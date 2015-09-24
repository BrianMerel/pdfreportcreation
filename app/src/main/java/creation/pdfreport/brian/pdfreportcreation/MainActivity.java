package creation.pdfreport.brian.pdfreportcreation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.pdf.PdfDocument.Page;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import static android.graphics.Paint.Style.*;


public class MainActivity extends Activity {

    PdfDocument document = new PdfDocument();


    //create a page description

    PdfDocument.PageInfo.Builder build = new PdfDocument.PageInfo.Builder(612,828,1);
    PdfDocument.PageInfo pageInfo;
    Paint stroke = new Paint();
    Paint strokefill = new Paint();
    Paint fill = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        build.setContentRect(new Rect(0, 0, 612, 828));

        Page page = document.startPage(build.create());



        Canvas canvas = page.getCanvas();

            stroke.setStyle(Style.STROKE);
            strokefill.setStyle(Style.FILL_AND_STROKE);
            fill.setStyle(Style.FILL);
            fill.setTextSize(10);
            strokefill.setColor(Color.parseColor("#d3d3d3"));
        //2/3 inch margin left and right
        canvas.drawLine(48f, 100f, 564f, 100f, stroke);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                canvas.drawBitmap(bitmap, null, new Rect(48, 20, 236, 95), stroke);
        //Textbox Dimension of 55 H x 156 L
        canvas.drawRect(252,45,408,100,stroke);
        canvas.drawRect(408,45,564,100,strokefill);
        canvas.drawRect(408, 45, 564, 100, stroke);

        canvas.drawText("Hey\nNitwit\nSay hello",408,50,fill);





        document.finishPage(page);

        FileOutputStream fileout;
        try {
            fileout = new FileOutputStream("/storage/emulated/0/Alarms/flavor.pdf");
            document.writeTo(fileout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
