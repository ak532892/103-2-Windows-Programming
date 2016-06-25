package com.example.user.tutorial2d;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;


class GraphicObject{
    private Bitmap _bitmap;
    private Coordinates _coordinates;
    private Speed _speed;

    public GraphicObject(Bitmap bitmap){
        _bitmap = bitmap;
        _coordinates = new Coordinates();
        _speed = new Speed();
    }

    public Speed getSpeed(){
        return _speed;
    }

    public Bitmap getGraphic(){
        return _bitmap;
    }

    public Coordinates getCoordinates(){
        return _coordinates;
    }

    //coordinates of the graphic
    public class Coordinates{
        private int _x = 100;
        private int _y = 0;

        public int getX(){
            return _x + _bitmap.getWidth() / 2;
        }

        public void setX(int value){
            _x = value - _bitmap.getWidth() / 2;
        }

        public int getY(){
            return _y + _bitmap.getHeight() / 2;
        }

        public void setY(int value){
            _y = value - _bitmap.getHeight() / 2;
        }

        public String toString(){
            return "Coordinates: (" + _x + "/" + _y + ")";
        }
    }
    public class Speed{
        public static final int X_DIRECTION_RIGHT = 1;
        public static final int X_DIRECTION_LEFT = -1;
        public static final int Y_DIRECTION_DOWN = 1;
        public static final int Y_DIRECTION_UP = -1;

        private int _x = 1;
        private int _y = 1;

        private int _xDirection = X_DIRECTION_RIGHT;
        private int _yDirection = Y_DIRECTION_DOWN;

        public int getXDirection(){
            return _xDirection;
        }

        public void setXDirection(int direction){
            _xDirection = direction;
        }

        public void toggleXDirection(){
            if(_xDirection == X_DIRECTION_RIGHT){
                _xDirection = X_DIRECTION_LEFT;
            }
            else{
                _xDirection = X_DIRECTION_RIGHT;
            }
        }

        public int getYDirection(){
            return _yDirection;
        }

        public void setYDirection(int direction){
            _yDirection = direction;
        }

        public void toggleYDirection(){
            if(_yDirection == Y_DIRECTION_DOWN){
                _yDirection = Y_DIRECTION_UP;
            }
            else{
                _yDirection = Y_DIRECTION_DOWN;
            }
        }

        public int getX(){
            return _x;
        }

        public void setX(int speed){
            _x = speed;
        }

        public int getY(){
            return _y;
        }

        public void setY(int speed){
            _y = speed;
        }
    }
}
public class Tutorial2D extends Activity {
    /** Called when the activity is first created. */
    long startTime = System.currentTimeMillis();
    long appearTime = System.currentTimeMillis();
    int score = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
    }
    class Panel extends SurfaceView implements SurfaceHolder.Callback {
        private TutorialThread _thread;
        private ArrayList<GraphicObject> _graphics = new ArrayList<GraphicObject>();
        public Panel(Context context) {
            super(context);
            getHolder().addCallback(this);
            _thread = new TutorialThread(getHolder(), this);
            setFocusable(true);
        }
        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);
            Bitmap bitmap;
            GraphicObject.Coordinates coords;

            //1s = 1000
            //0.3s出現一隻
            int timee = 300;
            //10s過後, 0.5s出現一隻
            if(System.currentTimeMillis()-startTime >= 10000)
                timee = 500;
            if ((System.currentTimeMillis() - appearTime) >= timee) {
                appearTime = System.currentTimeMillis();
                GraphicObject graphic = null;

                Random ran = new Random();
                int icon = ran.nextInt(3);
                if(icon == 0)
                    graphic = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                if(icon == 1)
                    graphic = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.icon2));
                if(icon == 2)
                    graphic = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.icon3));

                int where = ran.nextInt(4)+1;
                int direction = ran.nextInt(2);

                if(where == 1){
                    graphic.getCoordinates().setX( ran.nextInt(getWidth()+1) );
                    graphic.getCoordinates().setY(0);
                    if(direction == 0){
                        graphic.getSpeed().setXDirection(-1);
                        graphic.getSpeed().setYDirection(1);
                    }
                    else{
                        graphic.getSpeed().setXDirection(1);
                        graphic.getSpeed().setYDirection(1);
                    }
                }
                else if (where == 2){
                    graphic.getCoordinates().setX( getWidth() );
                    graphic.getCoordinates().setY( ran.nextInt(getHeight()+1) );
                    if(direction == 0){
                        graphic.getSpeed().setXDirection(-1);
                        graphic.getSpeed().setYDirection(-1);
                    }
                    else{
                        graphic.getSpeed().setXDirection(-1);
                        graphic.getSpeed().setYDirection(1);
                    }
                }
                else if (where == 3){
                    graphic.getCoordinates().setX( ran.nextInt(getWidth() + 1) );
                    graphic.getCoordinates().setY( getHeight() );
                    if(direction == 0){
                        graphic.getSpeed().setXDirection(1);
                        graphic.getSpeed().setYDirection(-1);
                    }
                    else{
                        graphic.getSpeed().setXDirection(-1);
                        graphic.getSpeed().setYDirection(-1);
                    }
                }
                else{
                    graphic.getCoordinates().setX( 0 );
                    graphic.getCoordinates().setY( ran.nextInt(getHeight()+1) );
                    if(direction == 0){
                        graphic.getSpeed().setXDirection(1);
                        graphic.getSpeed().setYDirection(-1);
                    }
                    else{
                        graphic.getSpeed().setXDirection(1);
                        graphic.getSpeed().setYDirection(1);
                    }
                }

                graphic.getSpeed().setX( (int)((System.currentTimeMillis() - startTime)/1500.) );
                graphic.getSpeed().setY( (int)((System.currentTimeMillis() - startTime)/1500.) );
                if(graphic.getSpeed().getX() <=1) {
                    graphic.getSpeed().setX( 1 );
                    graphic.getSpeed().setY( 1 );
                }
                //(int) event.getX() - graphic.getGraphic().getWidth() / 2
                //碰觸的點
                //(int) event.getY() - graphic.getGraphic().getHeight() / 2
                //碰觸的點
                _graphics.add(graphic);
            }
            for (GraphicObject graphic : _graphics) {
                bitmap = graphic.getGraphic();
                coords = graphic.getCoordinates();
                canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
            }

            //20s結束
            if(System.currentTimeMillis()-startTime >= 20000){
                _thread.setRunning(false);
                _graphics.clear();
                Paint mypaint =new Paint();
                //設定Paint屬性
                canvas.drawColor(Color.WHITE);
                mypaint.setColor(Color.RED);
                mypaint.setTextSize(40);
                canvas.drawText("Your Score: " + score, getWidth() / 2, getHeight() / 2, mypaint);
            }
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            synchronized (_thread.getSurfaceHolder()) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    for (GraphicObject graphic : _graphics) {
                        int touchX = (int)event.getX() - graphic.getGraphic().getWidth() / 2;
                        int touchY = (int)event.getY() - graphic.getGraphic().getHeight() / 2;
                        int graX = graphic.getCoordinates().getX();
                        int graY = graphic.getCoordinates().getY();
                        int range = 60;

                        if((touchX+range >= graX) && (graX >= touchX-range)){
                            if((touchY+range >= graY) && (graY >= touchY-range)) {
                                Log.d("touch", "SUCCESS");
                                score++;
                                _graphics.remove(graphic);
                                break;
                            }
                        }
                    }
                }
                return true;
            }
        }
        public void updatePhysics() {
            GraphicObject.Coordinates coord;
            GraphicObject.Speed speed;
            for (GraphicObject graphic : _graphics) {
                coord = graphic.getCoordinates();
                speed = graphic.getSpeed();
                // Direction
                if (speed.getXDirection() == GraphicObject.Speed.X_DIRECTION_RIGHT) {
                    coord.setX(coord.getX() + speed.getX());
                } else {
                    coord.setX(coord.getX() - speed.getX());
                }
                if (speed.getYDirection() == GraphicObject.Speed.Y_DIRECTION_DOWN) {
                    coord.setY(coord.getY() + speed.getY());
                } else {
                    coord.setY(coord.getY() - speed.getY());
                }
                // borders for x...
                if (coord.getX() < 0) {
                    speed.toggleXDirection();
                    coord.setX(-coord.getX());
                } else if (coord.getX() + graphic.getGraphic().getWidth() > getWidth()) {
                    speed.toggleXDirection();
                    coord.setX(coord.getX() + getWidth() - (coord.getX() + graphic.getGraphic().getWidth()));
                }
                // borders for y...
                if (coord.getY() < 0) {
                    speed.toggleYDirection();
                    coord.setY(-coord.getY());
                } else if (coord.getY() + graphic.getGraphic().getHeight() > getHeight()) {
                    speed.toggleYDirection();
                    coord.setY(coord.getY() + getHeight() - (coord.getY() + graphic.getGraphic().getHeight()));
                }
            }
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // TODO Auto-generated method stub
        }
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            _thread.setRunning(true);
            _thread.start();
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            _thread.setRunning(false);
            while (retry) {
                try {
                    _thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                // we will try it again and again...
                }
            }
        }
    }
    class TutorialThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private Panel _panel;
        private boolean _run = false;
        public TutorialThread(SurfaceHolder surfaceHolder, Panel panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }
        public SurfaceHolder getSurfaceHolder() {
            return _surfaceHolder;
        }
        public void setRunning(boolean run) {
            _run = run;
        }
        @Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    c = _surfaceHolder.lockCanvas(null);
                    synchronized (_surfaceHolder) {
                        _panel.updatePhysics();
                        _panel.onDraw(c);
                    }
                } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}