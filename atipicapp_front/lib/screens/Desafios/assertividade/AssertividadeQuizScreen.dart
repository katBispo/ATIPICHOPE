import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:flutter/services.dart';

class VideoQuizScreen extends StatefulWidget {
  @override
  _VideoQuizScreenState createState() => _VideoQuizScreenState();
}

class _VideoQuizScreenState extends State<VideoQuizScreen> {
  late VideoPlayerController _controller;
  bool _showQuiz = false;

  @override
  void initState() {
    super.initState();

    // Força rotação horizontal
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.landscapeRight,
    ]);

    _controller =
        VideoPlayerController.asset('assets/videos/ASSERTIVIDADE_2.mp4')
          ..initialize().then((_) {
            setState(() {});
            _controller.play();
          });

    _controller.addListener(() {
      if (!_showQuiz &&
          _controller.value.position >= _controller.value.duration) {
        setState(() {
          _showQuiz = true;
        });
      }
    });
  }

  @override
  void dispose() {
    // Restaura rotação para retrato
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
            ? "Parabéns, essa é a resposta mais assertiva!"
            : "Essa resposta não é a mais assertiva. Tente novamente."),
        actions: [
          TextButton(
            onPressed: () => Navigator.of(context).pop(),
            child: Text("OK"),
          ),
        ],
      ),
    );
  }

  final List<Map<String, dynamic>> _quizOptions = [
    {
      "label": "A)",
      "text": "Você não percebe que está me atrapalhando? Me deixa em paz!",
      "isCorrect": false
    },
    {
      "label": "B)",
      "text": "Estou muito ocupado, podemos falar mais tarde?",
      "isCorrect": false
    },
    {
      "label": "C)",
      "text": "“Estou muito ocupado, podemos falar mais tarde?”",
      "isCorrect": true
    },
  ];

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
                      Image.asset('assets/capas_botoes/assertividade_2.png',
                          fit: BoxFit.cover),
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
                                    _checkAnswer(option["isCorrect"]),
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
