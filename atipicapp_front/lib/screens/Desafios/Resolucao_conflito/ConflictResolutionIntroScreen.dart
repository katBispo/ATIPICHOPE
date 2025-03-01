import 'package:atipicapp/screens/Desafios/Resolucao_conflito/ConfilctResolutionQuizScreen.dart';
import 'package:flutter/material.dart';

class ConflictResolutionIntroScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFFFAEC76), // Fundo amarelo suave
      body: Padding(
        padding: EdgeInsets.all(20),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // Ícone principal representando conflito/resolução
            Icon(Icons.handshake, size: 100, color: Color(0xFF0297B2)),

            SizedBox(height: 20),

            // Título
            Text(
              "Nível de Resolução de Conflitos",
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
                color: Color(0xFFE33519), // Vermelho vibrante
              ),
            ),

            SizedBox(height: 20),

            // Texto explicativo
            Text(
              "Bem-vindo ao desafio! Você enfrentará situações fictícias baseadas em desafios reais do dia a dia. Sua missão é escolher como reagiria em cada cenário. Suas respostas revelarão seu estilo de resolução de conflitos!",
              textAlign: TextAlign.center,
              style: TextStyle(fontSize: 16, color: Colors.black87),
            ),

            SizedBox(height: 30),

            // Ícones representando diferentes reações a conflitos
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                Column(
                  children: [
                    Icon(Icons.sentiment_very_satisfied, size: 50, color: Color(0xFFB9E383)),
                    Text("Acalmar"),
                  ],
                ),
                Column(
                  children: [
                    Icon(Icons.sentiment_neutral, size: 50, color: Colors.orange),
                    Text("Negociar"),
                  ],
                ),
                Column(
                  children: [
                    Icon(Icons.sentiment_very_dissatisfied, size: 50, color: Color(0xFFE33519)),
                    Text("Confrontar"),
                  ],
                ),
              ],
            ),

            SizedBox(height: 40),

            // Botão para iniciar o desafio
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => Confilctresolutionquizscreen()),
              );
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Color(0xFF0297B2), // Azul vibrante
                padding: EdgeInsets.symmetric(horizontal: 40, vertical: 15),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30),
                ),
              ),
              child: Text(
                "Começar Desafio",
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
              ),
            ),
            SizedBox(height: 20),

   // Botão de voltar
            ElevatedButton.icon(
              onPressed: () {
                Navigator.pop(context); // Voltar para a tela anterior
              },
              icon: Icon(Icons.arrow_back, color: Colors.white),
              label: Text(
                "Voltar",
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
              ),
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.grey[800], // Cor escura para contraste
                padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30),
                ),
              ),
            ),


          ],
        ),
      ),
    );
  }
}
