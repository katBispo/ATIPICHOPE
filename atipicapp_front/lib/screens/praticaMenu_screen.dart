import 'package:flutter/material.dart';
import './Desafios/Resolucao_conflito/conflictResolutionIntroScreen.dart'; // Caminho corrigido
import './Desafios/empatia/EmpatiaIntroScreen.dart'; // Caminho corrigido
import './Desafios/trabalho_equipe/TrabalhoEquipeIntroScreen.dart'; // Caminho corrigido
import './Desafios/assertividade/AssertividadeIntroScreen.dart'; // Caminho corrigido
import './Desafios/comunicacao/ComunicacaoIntroScreen.dart'; // Caminho corrigido

class PraticaMenuScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("O que vamos praticar hoje?"),
        backgroundColor: Colors.teal,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  _buildCircle(Icons.favorite, "Empatia", () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => EmpatiaIntroScreen()),
                    );
                  }),
                  SizedBox(width: 20),
                  _buildCircle(Icons.record_voice_over, "Comunicação", () {
                     Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => ComunicacaoIntroScreen()),
                    );
                  }),
                ],
              ),
              SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  _buildCircle(Icons.check_circle, "Assertividade", () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => AssertividadeIntroScreen()),
                    );   
                  }),
                  SizedBox(width: 20),
                  _buildCircle(Icons.groups, "Trabalho em equipe", () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => TrabalhoEquipeIntroScreen()),
                    );                  
                    }),
                ],
              ),
              SizedBox(height: 20),

              // Botão de Resolução de Conflitos
              GestureDetector(
                onTap: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => ConflictResolutionIntroScreen(),
                    ),
                  );
                },
                child: Column(
                  children: [
                    Container(
                      height: 100,
                      width: 100,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        shape: BoxShape.circle,
                        boxShadow: [
                          BoxShadow(
                            color: Colors.black26,
                            blurRadius: 10,
                            spreadRadius: 2,
                            offset: Offset(0, 4),
                          ),
                        ],
                      ),
                      child: Center(
                        child: Icon(
                          Icons.handshake,
                          size: 50,
                          color: Colors.teal[700],
                        ),
                      ),
                    ),
                    SizedBox(height: 10),
                    Text(
                      "Resolução de Conflitos",
                      style:
                          TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
              ),

              SizedBox(height: 20),

              // Botão de Voltar
              ElevatedButton.icon(
                onPressed: () {
                  Navigator.pop(context); // Voltar para a tela anterior
                },
                icon: Icon(Icons.arrow_back, color: Colors.white),
                label: Text(
                  "Voltar",
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.grey[700]!,
                  padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  // Função para criar os círculos com navegação
  Widget _buildCircle(IconData icon, String label, VoidCallback onTap) {
    return GestureDetector(
      onTap: onTap,
      child: Column(
        children: [
          Container(
            height: 100,
            width: 100,
            decoration: BoxDecoration(
              color: Colors.white,
              shape: BoxShape.circle,
              boxShadow: [
                BoxShadow(
                  color: Colors.black26,
                  blurRadius: 10,
                  spreadRadius: 2,
                  offset: Offset(0, 4),
                ),
              ],
            ),
            child: Center(
              child: Icon(
                icon,
                size: 50,
                color: Colors.teal[700],
              ),
            ),
          ),
          SizedBox(height: 10),
          Text(
            label,
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
        ],
      ),
    );
  }
}
