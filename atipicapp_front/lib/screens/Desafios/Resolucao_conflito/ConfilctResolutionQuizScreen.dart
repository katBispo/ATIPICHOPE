import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:video_player/video_player.dart';

class Confilctresolutionquizscreen extends StatefulWidget {
  @override
  _ConfilctresolutionquizscreenState createState() => _ConfilctresolutionquizscreenState();
}

class _ConfilctresolutionquizscreenState extends State<Confilctresolutionquizscreen> {
  late VideoPlayerController _controller;
  bool _showQuiz = false;

  final List<Map<String, dynamic>> _quizOptions = [
    {
      "label": "A)",
      "text": " Ignorar e continuar a reunião  ",
      "isCorrect": false,
    },
    {
      "label": "B)",
      "text": "Intervir e pedir que ambos se acalmem ",
      "isCorrect": true,
    },
    {
      "label": "C)",
      "text": "Sugerir que um dos dois saia da sala  ",
      "isCorrect": false,
    },
     {
      "label": "D)",
      "text": "Tomar partido de um dos lados ",
      "isCorrect": false,
    },
  ];

  @override
  void initState() {
    super.initState();

    // Força rotação horizontal
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.landscapeRight,
    ]);

    _controller = VideoPlayerController.asset('assets/videos/RESOLUCAO_CONFLITO_1.mp4')
      ..initialize().then((_) {
        setState(() {});
        _controller.play();
      });

    _controller.addListener(() {
      if (!_showQuiz && _controller.value.position >= _controller.value.duration) {
        setState(() {
          _showQuiz = true;
        });
      }
    });
  }

  @override
  void dispose() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
    _controller.dispose();
    super.dispose();
  }

  void _replayVideo() {
    setState(() {
      _showQuiz = false;
      _controller.seekTo(Duration.zero);
      _controller.play();
    });
  }

  void _checkAnswer(bool isCorrect) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(isCorrect ? "Correto!" : "Errado!"),
        content: Text(isCorrect
            ? "Boa escolha! Essa é uma forma construtiva de resolver conflitos."
            : "Essa resposta não é a ideal para resolução de conflitos. Tente novamente."),
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
      body: _controller.value.isInitialized
          ? Stack(
              fit: StackFit.expand,
              children: [
                if (!_showQuiz)
                  Center(
                    child: AspectRatio(
                      aspectRatio: _controller.value.aspectRatio,
                      child: VideoPlayer(_controller),
                    ),
                  ),
                if (_showQuiz)
                  Stack(
                    fit: StackFit.expand,
                    children: [
                      Image.asset(
                        'assets/capas_botoes/resolucao_conflito_1.png',
                        fit: BoxFit.cover,
                      ),
                      Positioned(
                        top: 20,
                        right: 20,
                        child: IconButton(
                          onPressed: _replayVideo,
                          icon: Icon(Icons.replay, size: 30),
                          color: Colors.white,
                          tooltip: 'Repetir vídeo',
                          style: IconButton.styleFrom(
                            backgroundColor: Colors.black.withOpacity(0.5),
                          ),
                        ),
                      ),
                      Align(
                        alignment: Alignment.bottomCenter,
                        child: Padding(
                          padding: const EdgeInsets.only(
                              bottom: 30.0, left: 16.0, right: 16.0),
                          child: Wrap(
                            spacing: 12,
                            runSpacing: 12,
                            alignment: WrapAlignment.center,
                            children: _quizOptions.map((option) {
                              return ElevatedButton(
                                onPressed: () =>
                                    _checkAnswer(option["isCorrect"] as bool),
                                style: ElevatedButton.styleFrom(
                                  backgroundColor: Colors.teal,
                                  padding: EdgeInsets.symmetric(
                                      vertical: 12, horizontal: 16),
                                  textStyle: TextStyle(fontSize: 16),
                                ),
                                child: Text(
                                  "${option["label"]} ${option["text"]}",
                                  textAlign: TextAlign.center,
                                ),
                              );
                            }).toList(),
                          ),
                        ),
                      ),
                    ],
                  ),
              ],
            )
          : Center(child: CircularProgressIndicator()),
    );
  }
}
