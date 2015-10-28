package s07140531.gmec.edu.cn.work3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import s07140531.gmec.edu.cn.work3.R;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView tv1,tv2;
    private CheckBox cb1,cb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.Text);
        tv1=(TextView) findViewById(R.id.weight);
        cb1=(CheckBox)findViewById(R.id.man);
        cb2=(CheckBox)findViewById(R.id.women);
        tv2=(TextView)findViewById(R.id.result);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();

    }
    private void registerEvent(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tv1.getText().toString().trim().equals("")) {
                    if (cb1.isChecked() || cb2.isChecked()) {
                        Double weight = Double.parseDouble(tv1.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果------\n");
                        if (cb1.isChecked()) {
                            sb.append("男性标准身高");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "厘米");

                        }
                        if (cb2.isChecked()) {
                            sb.append("女性标准身高:");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "厘米");

                        }
                        tv2.setText(sb.toString());

                    } else {
                        showMessage("请选择性别！");
                    }
                } else {
                    showMessage("请输入体重！");

                }
            }
        });
    }



    private double evaluateHeight(Double weight, String sex) {
        double height;
        if (sex=="男"){
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    private void showMessage(String s) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(s);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
