import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';

class Comunicaoquiz extends StatefulWidget {
  @override
  _VideoQuizScreenState createState() => _VideoQuizScreenState();
}

class _VideoQuizScreenState extends State<Comunicaoquiz> {
  late VideoPlayerController _controller;
  bool _showOptions = false;

  @override
  void initState() {
    super.initState();
        _controller = VideoPlayerController.asset('assets/videos/dTomarpartidodeumdoslados.mp4')      ..initialize().then((_) {
        setState(() {});
        _controller.play();
      });

    _controller.addListener(_videoListener);
  }

  void _videoListener() {
    if (!_showOptions && _controller.value.position >= _controller.value.duration) {
      setState(() {
        _showOptions = true;
      });
    }
  }

  @override
  void dispose() {
    _controller.removeListener(_videoListener);
    _controller.dispose();
    super.dispose();
  }

  void _checkAnswer(bool isCorrect) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(isCorrect ? "Correto!" : "Errado!"),
        content: Text(isCorrect ? "Parabéns, resposta certa!" : "Tente novamente."),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(),
            child: Text("OK"),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Desafio em Vídeo"),
        backgroundColor: Colors.teal,
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          _controller.value.isInitialized
              ? AspectRatio(
                  aspectRatio: _controller.value.aspectRatio,
                  child: VideoPlayer(_controller),
                )
              : Center(child: CircularProgressIndicator()),
          SizedBox(height: 20),
          if (_showOptions)
            Column(
              children: _buildOptions(),
            ),
        ],
      ),
    );
  }

  final List<Map<String, dynamic>> _mockOptions = [
    {"text": "Opção 1", "isCorrect": false},
    {"text": "Opção 2", "isCorrect": false},
    {"text": "Opção 3", "isCorrect": true},
    {"text": "Opção 4", "isCorrect": false},
  ];

  List<Widget> _buildOptions() {
    return _mockOptions.map((option) => _buildOption(option["text"], option["isCorrect"])).toList();
  }

  Widget _buildOption(String text, bool isCorrect) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 8, horizontal: 20),
      child: ElevatedButton(
        onPressed: () => _checkAnswer(isCorrect),
        child: Text(text, style: TextStyle(fontSize: 18)),
        style: ElevatedButton.styleFrom(
          backgroundColor: Colors.teal,
          padding: EdgeInsets.symmetric(vertical: 15, horizontal: 30),
        ),
      ),
    );
  }
}