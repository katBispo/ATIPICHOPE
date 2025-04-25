import 'package:flutter/material.dart';
import 'praticaMenu_screen.dart';
class MenuScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.tealAccent[100], // Fundo azul esverdeado claro
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // Espaço para a Logo
              Container(
                height: 120,
                width: 120,
                decoration: BoxDecoration(
                  color: Colors.white,
                  shape: BoxShape.circle,
                ),
                child: Icon(
                  Icons.image,
                  size: 60,
                  color: Colors.grey[400],
                ),
              ),
              SizedBox(height: 20),

              // Título
              Text(
                "Bem-vindo ao Atipic Hope",
                style: TextStyle(
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                  color: Colors.blue[900],
                ),
                textAlign: TextAlign.center,
              ),
              SizedBox(height: 30),

              // Botões do menu
              buildMenuButton(
                  context, "Meu Perfil", Icons.person, Colors.blue, () {}),

              buildMenuButton(
                context,
                "O que vamos exercitar hoje?",
                Icons.fitness_center,
                Colors.orange,
                () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => PraticaMenuScreen()),
                  );
                },
              ),

              buildMenuButton(context, "Meu Desempenho", Icons.bar_chart,
                  Colors.green, () {}),

              // Botão de Sair
              buildMenuButton(context, "Sair", Icons.exit_to_app, Colors.red,
                  () {
                Navigator.pop(context); // Sai da tela
              }),
            ],
          ),
        ),
      ),
    );
  }

  // Função para criar os botões do menu // colocar dps a os de widget em cima do icon
  Widget buildMenuButton(BuildContext context, String text, IconData icon,
      Color color, VoidCallback onTap) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 10),
      child: ElevatedButton(
        onPressed: onTap,
        style: ElevatedButton.styleFrom(
          backgroundColor: color,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(12),
          ),
          padding: EdgeInsets.symmetric(vertical: 15, horizontal: 30),
        ),
        child: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            Icon(icon, color: Colors.white),
            SizedBox(width: 10),
            Text(
              text,
              style: TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                  color: Colors.white),
            ),
          ],
        ),
      ),
    );
  }
}