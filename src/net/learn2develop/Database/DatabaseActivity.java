package net.learn2develop.Database;
/* Got all code from http://www.devx.com/wireless/Article/40842/0/page/3 */
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class DatabaseActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        DBAdapter db = new DBAdapter(this);
        
        //---add 2 titles---
        db.open();
        long id;
        id = db.insertTitle(
        		"0470285818",
        		"C# 2008 Programmer's Reference",
        		"Wrox");
        id = db.insertTitle(
        		"047017661X",
        		"Professional Windows Vista Gadgets Programming",
        		"Wrox");
        db.close();

        //---get all titles---
        db.open();
        Cursor c = db.getAllTitles();
        if (c.moveToFirst())
        {
        	do {
        		DisplayTitle(c);
        	} while (c.moveToNext());
        }
        db.close();

        //---get a title---
        db.open();
        c = db.getTitle(2);
        if (c.moveToFirst())
        {
        	DisplayTitle(c);
        }
        else
        {
        	Toast.makeText(this, "No title found",Toast.LENGTH_LONG).show();
        }
        db.close();
        
        //---update title---
        db.open();
        if (db.updateTitle(1,
        		"0470285818",
        		"C# 2008 Programmer's Reference",
        		"Wrox Press"))
        {
        	Toast.makeText(this, "Update successful.",Toast.LENGTH_LONG).show();
        }
        else
        {
        	Toast.makeText(this, "Update failed.",Toast.LENGTH_LONG).show();
        }
        //-------------------
        //---retrieve the same title to verify---
        c = db.getTitle(1);
        if (c.moveToFirst())
        {
        	DisplayTitle(c);
        }
        else
        {
        	Toast.makeText(this, "No title found",Toast.LENGTH_LONG).show();
        }
        //-------------------
        db.close();

        
      //---delete a title---
        db.open();
        if (db.deleteTitle(1))
        {
        	Toast.makeText(this, "Delete successful.",Toast.LENGTH_LONG).show();
        }
        else
        {
        	Toast.makeText(this, "Delete failed.",Toast.LENGTH_LONG).show();
        }
        db.close();
        
        
        
        
        
        
        
        
    }


    public void DisplayTitle(Cursor c)
    {
    	Toast.makeText(this,
    			"id: " + c.getString(0) + "\n" +
    					"ISBN: " + c.getString(1) + "\n" +
    					"TITLE: " + c.getString(2) + "\n" +
    					"PUBLISHER: " + c.getString(3),
    					Toast.LENGTH_LONG).show();
    }
}