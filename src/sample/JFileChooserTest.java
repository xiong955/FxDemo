package sample; /**
 * @author: xiong
 * @time: 2017/12/07
 * @说明:
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JFileChooserTest {

    private JFileChooser fc = new JFileChooser();
    private int flag;

    public String test() {
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
        String path = null;
        File f = null;
        try {
            flag = fc.showOpenDialog(null);
        } catch (HeadlessException head) {
            System.out.println("Open File Dialog ERROR!");
        }
        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            f = fc.getSelectedFile();
            path = f.getPath();
        }

        //以上获得选择的文件夹
        //若要判断其中是否还有其他目录，可以这样做
        boolean hasSubDir = false;
        File dir = new File(path);
        //获得改目录下的文件的文件名，如果没有的话，filesName.length()=0

        String[] filesName = dir.list();
        for (String aFilesName : filesName) {
            File temp = new File(path + "/" + aFilesName);
            if (temp.isDirectory()) {
                hasSubDir = true;
                break;
            }

        }
        return fc.getSelectedFile().toString();
    }
}