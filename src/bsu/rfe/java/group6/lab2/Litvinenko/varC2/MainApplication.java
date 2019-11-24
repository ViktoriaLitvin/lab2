package bsu.rfe.java.group6.lab2.Litvinenko.varC2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;

public class MainApplication extends JFrame {

    //Размеры окна
    private static final int WIDTH = 520;
    private static final int HEIGHT = 350;

    // Текстовые поля для считывания значений переменных
    private  JTextField textFieldX;
    private  JTextField textFieldY;
    private  JTextField textFieldZ;
    //текстовое поле для памяти

    //текстовые поля для разных ячеек памяти
    private JTextField textFieldMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;
    // Текстовое поле для отображения результата
    private JTextField textFieldResult;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioButtonsMem = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hBoxFormulaType = Box.createHorizontalBox();
    private Box hBoxMemoryType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memId = 1;

    //переменные для разных ячеек памяти
    private Double doubleMem1 = 0.0;
    private Double doubleMem2 = 0.0;
    private Double doubleMem3 = 0.0;

    //Создаем поле для изображения
    private JLabel labelImage = new JLabel();

    private Image image1 = ImageIO.read(new File("src/img1.bmp"));
    private Image image2 = ImageIO.read(new File("src/img2.bmp"));

    // Формула №1 для рассчѐта
    private Double calculate1(Double x, Double y, Double z){
        return (pow((log(pow(1+x,2))+cos(PI*pow(z,3))), sin(y)) + pow((exp(pow(z,2))+cos(exp(z))+sqrt(1/y)),1/x));
    }

    // Формула №2 для рассчѐта
    private Double calculate2(Double x, Double y, Double z) {
        return pow(cos(PI * pow(x, 3)) + log(pow(1 + y, 2)), 0.25) * (pow(E, pow(z, 2)) + sqrt(1 / x) + cos(pow(E, y)));
       }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButtonFormula(String buttonName, final int formulaId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainApplication.this.formulaId = formulaId;
                // вывод изображения в зависимости от выбранной кнопки
                if(MainApplication.this.formulaId == 1) {
                    labelImage.setIcon(new ImageIcon(image1));
                }
                else {
                    labelImage.setIcon(new ImageIcon(image2));
                }
            }
        });
        radioButtons.add(button);
        hBoxFormulaType.add(button);
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButtonMemory(String buttonName, final int memId){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainApplication.this.memId = memId;
                if (memId == 1) {
                    textFieldMem1.setText(doubleMem1.toString());
                } else if (memId == 2) {
                    textFieldMem2.setText(doubleMem2.toString());
                } else if (memId == 3) {
                    textFieldMem3.setText(doubleMem3.toString());
                }
            }
        });
        radioButtonsMem.add(button);
        hBoxMemoryType.add(button);
    }


    // Конструктор класса
    public MainApplication() throws IOException {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width-WIDTH)/2,
                (kit.getScreenSize().height-HEIGHT)/2);


        //первоначальные значения изображения
        labelImage.setIcon(new ImageIcon(image1));
        Box hBoxImg = Box.createHorizontalBox();
        hBoxImg.add(Box.createHorizontalGlue());
        hBoxImg.add(labelImage);
        hBoxImg.add(Box.createHorizontalGlue());

        //радио-кнопки формул
        hBoxFormulaType.add(Box.createHorizontalGlue());
        addRadioButtonFormula("Формула 1", 1);
        hBoxMemoryType.add(Box.createHorizontalStrut(10));
        addRadioButtonFormula("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Создать область с полями ввода для X, Y И Z
        JLabel labelForX = new JLabel("X: ");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y: ");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z: ");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hBoxVariables = Box.createHorizontalBox();
        hBoxVariables.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        hBoxVariables.add(Box.createHorizontalGlue());
        hBoxVariables.add(labelForX);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldX);
        hBoxVariables.add(Box.createHorizontalStrut(50));
        hBoxVariables.add(labelForY);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldY);
        hBoxVariables.add(Box.createHorizontalStrut(50));
        hBoxVariables.add(labelForZ);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldZ);
        hBoxVariables.add(Box.createHorizontalGlue());

        // Создать область для вывода значений переменных
        hBoxMemoryType = Box.createHorizontalBox();
        hBoxMemoryType.add(Box.createHorizontalGlue());
        textFieldMem1 = new JTextField("0.0", 15);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        textFieldMem2 = new JTextField("0.0", 15);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        textFieldMem3 = new JTextField("0.0", 15);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        hBoxMemoryType = Box.createHorizontalBox();
        hBoxMemoryType.add(Box.createHorizontalGlue());
        addRadioButtonMemory("Переменная 1", 1);
        hBoxMemoryType.add(Box.createHorizontalStrut(10));
        hBoxMemoryType.add(textFieldMem1);
        hBoxMemoryType.add(Box.createHorizontalStrut(15));
        addRadioButtonMemory("Переменная 2", 2);
        hBoxMemoryType.add(Box.createHorizontalStrut(10));
        hBoxMemoryType.add(textFieldMem2);
        hBoxMemoryType.add(Box.createHorizontalStrut(15));
        addRadioButtonMemory("Переменная 3", 3);
        radioButtonsMem.setSelected(radioButtonsMem.getElements().nextElement().getModel(), true);
        hBoxMemoryType.add(Box.createHorizontalStrut(10));
        hBoxMemoryType.add(textFieldMem3);
        hBoxMemoryType.add(Box.createHorizontalGlue());
        hBoxMemoryType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат: ");
        textFieldResult = new JTextField("0.0",20);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.add(labelForResult);
        hBoxResult.add(Box.createHorizontalStrut(10));
        hBoxResult.add(textFieldResult);
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if(formulaId == 1){
                        result = calculate1(x, y, z);
                    }
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainApplication.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
                textFieldMem1.setText("0.0");
                textFieldMem2.setText("0.0");
                textFieldMem3.setText("0.0");
                doubleMem1 = 0.0;
                doubleMem2 = 0.0;
                doubleMem3 = 0.0;
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(memId == 1) {
                    doubleMem1 = 0.0;
                    textFieldMem1.setText("0.0");
                }
                if(memId == 2) {
                    doubleMem2 = 0.0;
                    textFieldMem2.setText("0.0");
                }
                if(memId == 3) {
                    doubleMem3 = 0.0;
                    textFieldMem3.setText("0.0");
                }
            }
        });

        JButton buttonMP = new JButton("M+");
        buttonMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Double result = Double.parseDouble(textFieldResult.getText());
                if (memId == 1) {
                    doubleMem1 += result;
                    textFieldMem1.setText(Double.toString(doubleMem1));
                }
                else if (memId == 2) {
                    doubleMem2 += result;
                    textFieldMem2.setText(Double.toString(doubleMem2));
                }
                else if (memId == 3) {
                    doubleMem3 += result;
                    textFieldMem3.setText(Double.toString(doubleMem3));
                }
            }
        });

        Box hBoxButtons = Box.createHorizontalBox();
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonMC);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(buttonMP);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setBorder( BorderFactory.createLineBorder(Color.PINK));

        // Связать все боксы воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxImg);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxFormulaType);
        contentBox.add(hBoxVariables);
        contentBox.add(hBoxMemoryType);
        contentBox.add(hBoxResult);
        contentBox.add(hBoxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    // Главный метод класса
    public static void main(String[] args) throws IOException {
        MainApplication frame = new MainApplication();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}