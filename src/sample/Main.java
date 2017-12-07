package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    private TextField mEdit;
    private Button mBut1;
    private Button mBut2;
    private String file;

    private ChineseToPinYinUtil mChineseToPinYinUtil = new ChineseToPinYinUtil();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        initView(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initView(Parent parent) {
        this.mEdit = (TextField) parent.lookup("#et");
        this.mBut1 = (Button) parent.lookup("#but1");
        this.mBut2 = (Button) parent.lookup("#but2");
        this.mBut1.setOnAction(event -> {
            file = new JFileChooserTest().test();
            mEdit.appendText(file);
        });
        this.mBut2.setOnAction(event -> {
            int i = rename(file);
            if (i > 0) {
                new AlertBox().display("错误", "更名失败!");
            } else {
                new AlertBox().display("成功", "更名完成!");
            }
        });
    }


    private int rename(String str) {
        int i = 0;
        File file = new File(str);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return 1;
            } else {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        System.out.println("文件夹:" + file1.getAbsolutePath());
                        rename(file1.getAbsolutePath());
                    } else {
                        System.out.println(str);
                        File renameFile = new File(str + "\\" + mChineseToPinYinUtil.toHanyuPinyin(file1.getName()));
                        System.out.println("文件:" + renameFile);
                        boolean isTrue = file1.renameTo(renameFile);
                        if (!isTrue) {
                            i = i + 1;
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return i;
    }
}
