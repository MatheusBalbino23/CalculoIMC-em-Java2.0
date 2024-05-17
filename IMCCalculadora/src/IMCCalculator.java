import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

public class IMCCalculator extends JFrame {
    // Declaração dos componentes da interface gráfica
    private JTextField pesoField; // Campo de texto para entrada do peso
    private JTextField alturaField; // Campo de texto para entrada da altura
    private JLabel resultadoField; // Rótulo para exibir o resultado do IMC
    private JLabel classificacaoField; // Rótulo para exibir a classificação do IMC

    public IMCCalculator() {
        // Configurações da janela
        setTitle("Calculadora de IMC"); // Define o título da janela
        setSize(300, 200); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para fechar a janela no 'X' e encerrar a execução do programa
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new GridLayout(5, 2)); // Define a janela como uma grade de 5 linhas e 2 colunas

        // Criação e configuração dos componentes da interface
        JLabel pesoLabel = new JLabel("Peso (kg):"); 
        pesoField = new JTextField(); 
        JLabel alturaLabel = new JLabel("Altura (m):"); 
        alturaField = new JTextField(); 
        JButton calculateButton = new JButton("Calcular IMC"); // Botão para calcular o IMC
        resultadoField = new JLabel("IMC: "); // Rótulo para exibir o resultado do IMC
        classificacaoField = new JLabel("Classificação: "); // Rótulo para exibir a classificação do IMC

        // Adicionando componentes à janela
        add(pesoLabel); 
        add(pesoField); 
        add(alturaLabel); 
        add(alturaField); 
        add(new JLabel()); 
        add(calculateButton); 
        add(resultadoField); 
        add(classificacaoField); 

        // Ação do botão de calcular
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateIMC(); // Chama o método que calcula o IMC quando o botão é clicado
            }
        });
    }

    // Método que calcula o IMC e atualiza os rótulos de resultado e classificação
    private void calculateIMC() {
        try {
            double peso = Double.parseDouble(pesoField.getText()); // Obtém o peso do campo de texto
            double altura = Double.parseDouble(alturaField.getText()); // Obtém a altura do campo de texto
            double imc = peso / (altura * altura); // Calcula o IMC
            resultadoField.setText(String.format("IMC: %.2f", imc)); // Atualiza o rótulo do resultado do IMC
            verificarIMC(imc); // Chama o método que verifica a classificação do IMC
        } catch (NumberFormatException e) {
            // Mostra uma mensagem de erro se os valores inseridos não forem numéricos
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método que verifica a classificação do IMC e atualiza o rótulo de classificação
    private void verificarIMC(double imc) {
        String classificacao; 
        // Determina a classificação com base no valor do IMC
        if (imc < 18.5) {
            classificacao = "Abaixo do peso";
        } else if (imc >= 18.5 && imc <= 24.9) {
            classificacao = "Peso ideal (parabéns)";
        } else if (imc >= 25.0 && imc <= 29.9) {
            classificacao = "Levemente acima do peso";
        } else if (imc >= 30.0 && imc <= 34.9) {
            classificacao = "Obesidade grau 1";
        } else if (imc >= 35.0 && imc <= 39.9) {
            classificacao = "Obesidade grau 2 (severa)";
        } else {
            classificacao = "Obesidade grau 3 (mórbida)";
        }
        classificacaoField.setText("Classificação: " + classificacao); 
    }

    public static void main(String[] args) {
    	//é um método em Java utilizado para garantir que um pedaço de código que altera a interface gráfica do usuário (GUI) 
    	//seja executado no Event Dispatch Thread (EDT), que é o thread responsável por manipular a GUI em aplicações Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IMCCalculator().setVisible(true); // Cria uma instância da calculadora de IMC e torna a janela visível
            }
        });
    }
}
