import 'package:flutter/material.dart';

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
                  _buildCircle(Icons.favorite, "Empatia"),
                  SizedBox(width: 20),
                  _buildCircle(Icons.record_voice_over, "Comunicação"),
                ],
              ),
              SizedBox(height: 20),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  _buildCircle(Icons.check_circle, "Assertividade"),
                  SizedBox(width: 20),
                  _buildCircle(Icons.groups, "Trabalho em equipe"),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  // Função para criar os círculos
  Widget _buildCircle(IconData icon, String label) {
    return Column(
      children: [
        Container(
          height: 100,
          width: 100,
          decoration: BoxDecoration(
            color: Colors.teal[100],
            shape: BoxShape.circle,
          ),
          child: Icon(
            icon,
            size: 50,
            color: Colors.teal[700],
          ),
        ),
        SizedBox(height: 10),
        Text(
          label,
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
        ),
      ],
    );
  }
}
