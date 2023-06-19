import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.text.Highlighter.HighlightPainter;


class MyFrame extends JFrame {
       
    class CaseHandler {
        // method for converting text to Lower Case
          void lowerCase(ActionEvent ae) {
         notepad.replaceSelection(notepad.getSelectedText().toLowerCase());
        }
       // method for converting text to Upper Case
     void upperCase(ActionEvent ae) {
        notepad.replaceSelection(notepad.getSelectedText().toUpperCase());
       }   
   }
    
    class ButtonHandler {
        // method for replacing all occurences of selected text with the given text
         void replaceAll(ActionEvent ae){
             String s=notepad.getText();
             String s2 = notepad.getSelectedText();
             String s3 = replace_selected_text.getText();
                 s = s.replaceAll(s2,s3);
                 notepad.setText(s);
        }
        
        // method for finding the given text
         void find(ActionEvent ae) {
            Highlighter h = notepad.getHighlighter();
            HighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(new Color(199,125,255));
            int prev = notepad.getText().indexOf(find_selected_text.getText());
            int next = prev + find_selected_text.getText().length();
            try {
                h.addHighlight(prev, next, p);
            } catch (BadLocationException ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // method for replacing selected text with the given text
         void replace (ActionEvent ae) {
           notepad.replaceSelection(replace_selected_text.getText());
        }
    }
   
   
    class FileHandler {
         JFileChooser fileChooser = new JFileChooser();
         // method for loading the file into JTextPane
        void openingFile(ActionEvent ae) {
           try {
                FileReader fr;
             int return_value = fileChooser.showOpenDialog(null);
             if(return_value == JFileChooser.APPROVE_OPTION) {
              fr = new FileReader(fileChooser.getSelectedFile());
              notepad.read(fr, null);
             fr.close();
             }
            } catch (IOException ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
       // method for saving the current contents of JTextPane to a file
      void SavingFile(ActionEvent ae) {
            try {
                FileWriter fw;
             int return_value = fileChooser.showSaveDialog(null);
             if(return_value == JFileChooser.APPROVE_OPTION) {
              fw = new FileWriter(fileChooser.getSelectedFile());
             notepad.write(fw);
             fw.close();
             }
            } catch (IOException ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
    }
        
     class CountHandler {
         // method for counting no of words and characters in JTextPane
    public void wordCount(){
        int flag;
       String words = notepad.getText().trim();
       String chars = notepad.getText().replaceAll("[\s\n\r]","");
       int noOfWords = words.isEmpty()?0:words.split("\\s+").length;
       if(notepad.getSelectedText() == null) {
           flag = 0;
       labelForCountingChars.setText(String.valueOf(chars.length()));
       labelForWordCount.setText(String.valueOf(noOfWords));
       }
       else {
           flag = 1;
           words = notepad.getSelectedText().trim();
           noOfWords = words.isEmpty()?0:words.split("\\s+").length;
           chars = notepad.getSelectedText().replaceAll("[\s\n\r]","");
            labelForCountingChars.setText(String.valueOf(chars.length()));
            labelForWordCount.setText(String.valueOf(noOfWords));
       }
       if(flag==0)
           textLabel.setText("Count");
       else
           textLabel.setText("Selected Count");
       
    }
  }

    JPanel dashboard = new JPanel();
    JPanel embody = new JPanel();
    JTextArea notepad2 = new JTextArea();
    JMenuBar menu_bar = new JMenuBar();
    JMenu styleMenu = new JMenu("Font Styles");
    JMenu sizeMenu = new JMenu("Font Size");
    JTextPane notepad = new JTextPane();
    JScrollPane sb1 = new JScrollPane(notepad);
    JLabel labelForWordCount = new JLabel("");
    JLabel count_no_of_characters = new JLabel("Character Count: ");
    JLabel labelForCountingChars = new JLabel("");
    JLabel textLabel = new JLabel("");
    JMenu file = new JMenu("File");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem save = new JMenuItem("Save");
    JMenu casee = new JMenu("Change_Case");
    JLabel count_no_of_words = new JLabel("Word Count: ");
    JTextField find_selected_text = new JTextField();
    JTextField replace_selected_text = new JTextField();
    JScrollPane sb2 = new JScrollPane(find_selected_text);
    JScrollPane sb3 = new JScrollPane(replace_selected_text);
    JLabel labelForFind = new JLabel("Find Text");
    JLabel labelForReplace = new JLabel("Replace Selected Text");
    JLabel replaceWithEmpty = new JLabel("");
    JButton b1 = new JButton("Replace");
    JButton b2 = new JButton("Replace All");
    JButton b3 = new JButton("Find");
    JButton cut = new JButton("Cut");
    JButton copy = new JButton("Copy");
    JButton paste = new JButton("paste");
    JButton removeHighlight = new JButton("Remove highlight");
    JMenu _font = new JMenu("Font");
    JMenu _size = new JMenu("Size");
    JMenuItem upper_case = new JMenuItem("UPPER CASE");
    JMenuItem lower_case = new JMenuItem("lower case");
    Font defaul_t = new Font("Comic Sans MS",Font.PLAIN,15);
    JPanel p_for_hook = new JPanel();
    JPanel TogglePanel = new JPanel();
       public MyFrame () {
        super("Saideep's text Editor");
        notepad.setFont(defaul_t);
        find_selected_text.setFont(defaul_t);
        replace_selected_text.setFont(defaul_t);
        JPanel p1 = new JPanel();
        file.add(load);
        file.add(save);
        add(file);
        add(styleMenu);
        menu_bar.add(file);
        menu_bar.add(casee);
        menu_bar.add(styleMenu);
        menu_bar.add(sizeMenu);
        // Menu for changing Font Styles
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Arial","Arial"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Calibri","Calibri"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Cambria","Cambria"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Courier New","Courier New"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Comic Sans MS","Comic Sans MS"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Dialog","Dialog"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Georgia","Georgia"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Helevetica","Lucida Sans"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Monospaced","Monospaced"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("SignPainter","SignPainter"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Tahoma","Tahoma"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Times New Roman","Times New Roman"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("Verdana","Verdana"));
         JPanel box2 = new JPanel();
        // Menu for changing Font sizes  
        sizeMenu.add(new StyledEditorKit.FontSizeAction("3", 3));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("6", 6));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("9", 9));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("12", 12));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("15", 15));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("18", 18));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("21", 21));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("24", 24));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("27", 27));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("30", 30));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("33", 33));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("36", 36));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("39", 39));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("42", 42));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("45", 45));
        sizeMenu.add(new StyledEditorKit.FontSizeAction("50", 50));
        notepad.setBackground(new Color(255,202,200));
         JPanel containerWord = new JPanel();
          FileHandler fh = new FileHandler();
          
        // Registering menu items to Action Listener for loading and saving a file
        load.addActionListener((ActionEvent ae)-> {
                    fh.openingFile(ae);
        });
        save.addActionListener((ActionEvent ae)-> {
                    fh.SavingFile(ae);
        });
        
        notepad.addKeyListener(new MyKeyAdapter());
        notepad.addMouseListener(new MyMouseAdapter());
        notepad.addMouseMotionListener(new MyMouseMotionAdapter());
        
        CaseHandler ch = new CaseHandler();
        // Registering menu itmes to Action Listener for converting a text to upper or lower case
        upper_case.addActionListener((ActionEvent ae) -> {
            ch.upperCase(ae);
        });

        lower_case.addActionListener((ActionEvent ae) -> {
            ch.lowerCase(ae);
        });
        
         JPanel placeLeft = new JPanel();
         ButtonHandler bh = new ButtonHandler();
          // Registering the folloeing buttons to Action event Receiver
        b2.addActionListener((ActionEvent ae) -> {
            bh.replaceAll(ae);
        });
        b3.addActionListener((ActionEvent ae) -> {
            bh.find(ae);
        });
        b1.addActionListener((ActionEvent ae) -> {
            bh.replace(ae);
        });
        cut.addActionListener((ActionEvent ae) -> {
            notepad.cut();
        });
        copy.addActionListener((ActionEvent ae) -> {
            notepad.copy();
        });
        paste.addActionListener((ActionEvent ae) -> {
            notepad.paste();
        });
        removeHighlight.addActionListener((ActionEvent ae) -> {
            Highlighter h = notepad.getHighlighter();
            h.removeAllHighlights();
        });
        
        JPanel holder = new JPanel();
        JPanel placeRight = new JPanel();
        casee.add(upper_case);
        casee.add(lower_case);
        JButton bold_button = new JButton(new StyledEditorKit.BoldAction());
        bold_button.setText("Bold");
        bold_button.setBackground(new Color(251,250,205));
        JButton italic_button = new JButton(new StyledEditorKit.ItalicAction());
        italic_button.setText("Italic");
        italic_button.setBackground(new Color(249,249,249));
        JButton und_button = new JButton(new StyledEditorKit.UnderlineAction());
        und_button.setText("Underline");
        und_button.setBackground(new Color(255,228,192));
       cut.setBackground(new Color(204,213,174));
       copy.setBackground(new Color(233,237,201));
       paste.setBackground(new Color(254,250,224));
       b3.setBackground(new Color(236,204,178));
       b1.setBackground(new Color(255,219,164));
       b2.setBackground(new Color(255,233,174));
       removeHighlight.setBackground(new Color(250,237,205));
       p_for_hook.setLayout(new BoxLayout(p_for_hook,BoxLayout.LINE_AXIS));
       p_for_hook.add(bold_button);
       p_for_hook.add(italic_button);
       p_for_hook.add(und_button);
       find_selected_text.setBackground(new Color(222,245,229));
       replace_selected_text.setBackground(new Color(188,234,213));
       p_for_hook.setBackground(new Color(205,252,248));
       styleMenu.setBackground(new Color(225,255,177));
       sizeMenu.setBackground(new Color(199,242,164));
       menu_bar.setBackground(new Color(233,237,201));
        box2.setLayout(new BoxLayout(box2,BoxLayout.LINE_AXIS));
        placeLeft.setBackground(new Color(205,252,248));
        placeLeft.setLayout(new BoxLayout(placeLeft,BoxLayout.PAGE_AXIS));
        placeRight.setLayout(new BoxLayout(placeRight,BoxLayout.PAGE_AXIS));
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        TogglePanel.setBackground(new Color(205,252,246));
        JPanel tempo = new JPanel();
        JPanel buffer = new JPanel();
        find_selected_text.setPreferredSize(new Dimension(300,15));
        Border border_for_TextField  = BorderFactory.createLineBorder(Color.BLACK, 1,true);
        find_selected_text.setBorder(border_for_TextField);
        replace_selected_text.setPreferredSize(new Dimension(300,15));
        replace_selected_text.setBorder(border_for_TextField);
        TogglePanel.setLayout(new BoxLayout(TogglePanel,BoxLayout.PAGE_AXIS));
        tempo.setLayout(new BoxLayout(tempo, BoxLayout.PAGE_AXIS));
        tempo.add(labelForFind);
        tempo.add(find_selected_text);
        tempo.add(labelForReplace);
        tempo.add(replace_selected_text);
        TogglePanel.add(tempo);
        tempo.setBackground(new Color(222,245,229));
        buffer.setLayout(new BoxLayout(buffer, BoxLayout.LINE_AXIS));
        buffer.add(cut);
        buffer.add(copy);
        buffer.add(paste);
        buffer.add(b3);
        buffer.add(b1);
        buffer.add(b2);
        buffer.add(removeHighlight);
        TogglePanel.add(buffer);
        TogglePanel.add(replaceWithEmpty);
        holder.setLayout(new BoxLayout(holder,BoxLayout.LINE_AXIS));
        sb1.setPreferredSize(new Dimension(500,350));
        holder.add(sb1);
        sb1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sb1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        containerWord.setLayout(new FlowLayout(FlowLayout.LEFT));
        containerWord.add(count_no_of_words);
        containerWord.add(labelForWordCount);
        containerWord.add(count_no_of_characters);
        containerWord.add(labelForCountingChars);
        containerWord.add(textLabel);
        containerWord.setBackground(new Color(250,237,205));
        placeLeft.add(Box.createRigidArea(new Dimension(0,10)));
        placeLeft.add(p_for_hook);
        placeLeft.add(Box.createRigidArea(new Dimension(0,10)));
        placeLeft.add(TogglePanel);
        placeLeft.add(Box.createRigidArea(new Dimension(0,10)));
        placeLeft.add(holder);
        placeLeft.setBorder(BorderFactory.createLineBorder(Color.black));
        box2.add(placeLeft);
        box2.add(Box.createRigidArea(new Dimension(10,0)));
        box2.add(placeRight);
        embody.setLayout(new BoxLayout(embody,BoxLayout.LINE_AXIS));
        notepad2.setText("""
                                              ~~~~~~~~~~ Text Editor Manual ~~~~~~~~~~                        
                         Please refer to the functionalities: 
                         1) Enter a text in replace field and select the text to replace
                         2) Select a text and use Cut, Copy, Paste
                         3) Select the text to change its font style
                         4) Before writing text, select a font style to apply it to whole document
                         5) Points 3) and 4) apply for Bold, Italic and Underline
                         6) Points 3) and 4) apply for font sizes as well
                         7) Type in Find field and click on Find to search text
                         8) Select a text to change its case
                         9) When you find a text the particular text will be highlighted
                         10) If the text isn't found, it won't be highlighted
                         11) Click on Remove Highlight to remove Highlighted text
                         12) Select the text to change its case
                         """);
        notepad2.setFont(new Font("Comic Sans MS",Font.ITALIC,12));
        notepad2.setBackground(new Color(227,213,202));
        notepad2.setEditable(false);
        embody.add(notepad2);
        placeRight.add(embody);
        add(menu_bar,BorderLayout.NORTH);
        p1.add(box2);
        p1.add(Box.createVerticalGlue());
        p1.add(containerWord);
        add(p1);
    }
        // Adapter class for handling word count without selection of text
        class MyKeyAdapter extends KeyAdapter {
             CountHandler ct = new CountHandler();
             public void keyReleased(KeyEvent e) {
                ct.wordCount();
            }
        }
        // Adapter class for handling word count with selection of text
        class MyMouseAdapter extends MouseAdapter {
            CountHandler ct = new CountHandler();
            public void mouseClicked(MouseEvent e) {
                ct.wordCount();
            }
        }
        class MyMouseMotionAdapter extends MouseMotionAdapter {
              CountHandler ct = new CountHandler();
              public void mouseDragged(MouseEvent e) {
                ct.wordCount();
            }
       }
}

public class saideep {
    public static void main(String[] args) {
        JFrame Myframe = new MyFrame();
        Myframe.setSize(1000,600);
        Myframe.setVisible(true);
        Myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}



 