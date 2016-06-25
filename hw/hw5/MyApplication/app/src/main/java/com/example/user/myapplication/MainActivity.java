package com.example.user.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    TableLayout tableLayout;
    private String PATH =getSDPath()+"/foo.ser";
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> tmp = new ArrayList<String>();
    TableData tabledata = new TableData();
    String s;

    int choosItem;
    int last;
    int color;
    int gettableCount;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout1);
        this.registerForContextMenu(tableLayout);
    }

    private void BuildTable() {
        //build all list
        tableLayout.setBackgroundColor(Color.WHITE);
        for (int i = 0; i < list.size(); i += 3) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 3; j++) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                tv.setPadding(5, 5, 5, 5);
                s = list.get(i+j);
                boolean valid = checkValid(j);
                if(valid)
                    //color = 0xFFCCFF3;
                    color = Color.BLACK;
                else
                    color = 0xFFFF0000;
                //tv.setBackgroundColor(color);
                tv.setTextColor(color);
                tv.setText(s);
                row.addView(tv);

                if(i%2==0)
                    row.setBackgroundColor(0xFFEFF7FF);
                else
                    row.setBackgroundColor(0xFFC5E6FF);
            }
            row.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    choosItem= tableLayout.indexOfChild(view);
                    if (tableLayout.getChildCount()>last )
                        tableLayout.getChildAt(last).setBackgroundColor( (last%2==0) ? 0xFFC5E6FF : 0xFFEFF7FF );
                    Toast.makeText(getApplicationContext(), "Selected row is " + choosItem, Toast.LENGTH_LONG).show();
                    //row
                    last=choosItem;
                    //tv.setBackgroundColor(Color.DKGRAY);
                    view.setBackgroundColor(Color.DKGRAY);
                }
            });


            tableLayout.addView(row);
        }
        list2.clear();
    }

    private void BuildTable2(final int status) {
        //build new list2
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        for (int j = 0; j < 3; j++) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            tv.setPadding(5, 5, 5, 5);
            s = list2.get(j);
            boolean valid = checkValid(j);
            int color;
            if(valid)
                //color = 0xFFCCFF3;
                color = Color.BLACK;
            else
                color = 0xFFFF0000;
            //tv.setBackgroundColor(color);
            tv.setTextColor(color);
            tv.setText(s);
            row.addView(tv);
            //row.addView(tv,choosItem-1);
        }

        row.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                choosItem = tableLayout.indexOfChild(view);
                if (tableLayout.getChildCount()>last )
                    tableLayout.getChildAt(last).setBackgroundColor( (last%2==0) ?  0xFFC5E6FF : 0xFFEFF7FF  );
                Toast.makeText(getApplicationContext(), "Selected row is " + choosItem, Toast.LENGTH_LONG).show();
                //row
                last = choosItem;
                //tv.setBackgroundColor(Color.DKGRAY);
                view.setBackgroundColor(Color.DKGRAY);
            }
        });


        if (status == 1) {

            tableLayout.addView(row, choosItem);
            tableLayout.getChildAt(choosItem).setBackgroundColor((choosItem % 2 == 0) ? 0xFFC5E6FF : 0xFFEFF7FF );

        }
        else if (status == 2) {
            tableLayout.addView(row);
            gettableCount=tableLayout.getChildCount()-1;
            tableLayout.getChildAt(gettableCount).setBackgroundColor((gettableCount % 2 == 0) ? 0xFFC5E6FF : 0xFFEFF7FF );
        }

        list2.clear();
    }
    public boolean checkValid(int num){
        if(num == 0) {
            if (s == null) {
                return false;
            }
            String namePattern = "[a-zA-Z]+";
            return s.matches(namePattern);
        }
        if(num == 1) {
            // 判斷是否為E-mail
            if (s == null) {
                return false;
            }
            String emailPattern = "^([\\w]+)(([-\\.][\\w]+)?)*@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            return s.matches(emailPattern);
        }
        if(num == 2) {
            // 判斷是否為手機號碼(xxxx-xxx-xxx)
            if (s == null) {
                return false;
            }
            String cellPattern = "[0-9]{4}-[0-9]{3}-[0-9]{3}";
            return s.matches(cellPattern);
        }
        return true;
    }

    public void listConnect() {
        for(int i = 0; i < 3; i++) {
            s = list2.get(i);
            list.add(s);
        }
    }

    public void addListener(final int status) {

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText s1 = (EditText) promptsView
                .findViewById(R.id.input_name);

        final EditText s2 = (EditText) promptsView
                .findViewById(R.id.input_email);

        final EditText s3 = (EditText) promptsView
                .findViewById(R.id.input_phone);





        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        // edit text
                        //tableLayout.removeView(tableLayout.getChildAt(choosItem));
                        if (status == 1) {
                            deleteListener();
                            int i = choosItem*3;
                            list.add(i-3,s1.getText().toString());
                            list.add(i-2,s2.getText().toString());
                            list.add(i-1,s3.getText().toString());
                        }
                        list2.add(s1.getText().toString());
                        list2.add(s2.getText().toString());
                        list2.add(s3.getText().toString());
                        if (status != 1)
                            listConnect();
                        BuildTable2(status);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void editListener(){
        addListener(1);
    }

    public void deleteListener() {
        //int i = list.size();
        int i = choosItem*3;
        if(i >= 3) {
            list.remove(i - 1);
            list.remove(i - 2);
            list.remove(i - 3);

            if(tableLayout.getChildCount() > 1) {
                //tableLayout.removeView(tableLayout.getChildAt(tableLayout.getChildCount() - 1));
                tableLayout.removeView(tableLayout.getChildAt(choosItem));

            }
        }
    }

    public void deleteAll() {
        int tt = tableLayout.getChildCount();
        for(int i = tt; i >= 1; i--) {
            tableLayout.removeView(tableLayout.getChildAt(i));
        }
    }

    class TableData implements Serializable {
        private static final long serialVersionUID = 1L;
        ArrayList<String> list0 = new ArrayList<String>();;

        public TableData()
        {

        }

        private void writeObject(java.io.ObjectOutputStream stream)
                throws IOException {
            stream.writeObject(getlist());
            //stream.writeObject(getD());
        }
        private void readObject(java.io.ObjectInputStream stream)
                throws IOException,ClassNotFoundException
        {
            setlist((ArrayList<String>) stream.readObject());
            // setD((Date) stream.readObject());
        }/*
        public void setD(Date d)
        {
            this.date=d;
        }
        public Date getD()
        {
            return date;
        }*/
        public void setlist(ArrayList<String> list2)
        {
            this.list0 = list2;
        }
        public ArrayList<String> getlist()
        {
            return list0;
        }
        public String toString(){
            return "QQQQQQQQQQQ";
        }
    }

    public String getSDPath(){

        File sdDir = null;
        boolean sdCardExist= Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        //判斷SD是否存在
        if(sdCardExist) {
            sdDir=Environment.getExternalStorageDirectory();
        }
        return sdDir.toString();
    }

    public TableData Import(String path)
    {
        TableData tabledata=null;
        try {
            File file = new File(path);
            FileInputStream fl = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fl);

            tabledata = (TableData)ois.readObject();

            fl.close();
            ois.close();
            //return person2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }/*
        finally{
            if (ois!=null){
                ois.close();}
        }*/
        return tabledata;
        //return null;
    }
    public void Export(TableData tabledata2)
    {
        try {
            //TableData.setD(new Date());
            NowListclonetoLoadList(tabledata2);

            File file = new File(PATH);
            FileOutputStream fs = new FileOutputStream(file/*,true*/);
            ObjectOutputStream oos = new ObjectOutputStream(fs);

            oos.reset();
            oos.writeObject(tabledata2);

            oos.flush();
            oos.close();
            fs.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public  void NowListclonetoLoadList(TableData tabledata2)
    {
        //list.clear();
        tabledata2.getlist().clear();
        for(int c=0;c<list.size();c++) {
            tabledata2.getlist().add(list.get(c));
        }

    }
    public  void LoadListclonetoNowList(ArrayList<String> src)
    {
        list.clear();

        for(int c=0;c<src.size();c++) {
            list.add(src.get(c));
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEdit:
                if(choosItem>0)
                    editListener();
                return true;
            case R.id.menuDelete:
                if(choosItem>0)
                    deleteListener();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_main, contextMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.menuImport:
                //import list
                tabledata = Import(PATH);
                deleteAll();
                LoadListclonetoNowList(tabledata.getlist());

                //LoadListclonetoNowList(tabledata.getlist());
                //ArrayList<String>
                //public static List<String>{}
                BuildTable();
                return true;
            case R.id.menuExport:
                //export list
                Export(tabledata);
                return true;
            case R.id.menuAdd:
                addListener(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}