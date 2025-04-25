import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:video_player/video_player.dart';

class Trabalhoequipequizscreen extends StatefulWidget {
  @override
  _TrabalhoequipequizscreenState createState() => _TrabalhoequipequizscreenState();
}

class _TrabalhoequipequizscreenState extends State<Trabalhoequipequizscreen> {
  late VideoPlayerController _controller;
  bool _showQuiz = false;

  final List<Map<String, dynamic>> _quizOptions = [
    {
      "label": "A)",
      "text": "Não se preocupe, faça no seu tempo",
      "isCorrect": false,
    },
    {
      "label": "B)",
      "text": "Você vai fazer sua tarefa, ou quer que faça a minha e a sua.",
      "isCorrect": false,
    },
    {
      "label": "C)",
      "text": "Será que você não consegue entregar nada no prazo",
      "isCorrect": false,
    },
     {
      "label": "D)",
      "text": "Podemos Resolver isso juntos, você precisa da minha ajuda?",
      "isCorrect": true,
    },
  ];

  @override
  void initState() {
    super.initState();

    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.landscapeRight,
    ]);

    _controller = VideoPlayerController.asset('assets/videos/TRABALHO_EQUIPE_1.mp4')
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
            ? "Boa escolha! Esse é um ótimo exemplo de trabalho em equipe."
            : "Essa resposta não é ideal para um bom trabalho em equipe. Tente novamente."),
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
                        'assets/capas_botoes/trabalho_equipe_1.png',
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
