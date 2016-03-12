/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficacion;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * @Fecha 11/03/2016
 * @author 
 *  Estrella Belen Cortes Delgado
 *  Cairo González Resendiz 
 *  Dalia Oropeza Bello
 */
public class Main {
    public static void main (String [] args){
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities cap= new GLCapabilities (profile);
        TableroAjedrez  canvas = new TableroAjedrez (cap);
        canvas.setSize(600,600);

        Frame frame = new Frame();
        frame.add(canvas);
        frame.setSize(canvas.getWidth(), canvas.getHeight());
        frame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e){
                new Thread(){

                public void run(){
                  System.exit(0);

                }
                }.start();

            }
        }   
                );
               frame.setVisible(true);
    }
}

public class TableroAjedrez extends GLCanvas implements GLEventListener{
    TableroAjedrez (GLCapabilities cap){
        super(cap);
        this.addGLEventListener(this);
}

    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_PROJECTION);

        gl.glLoadIdentity();


        gl.glFrustum(-5.0, 5.0, -5.0, 5.0, 1.5, 100);
        gl.glTranslatef(0.0f, 0.0f, -2.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {

    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();


        gl.glBegin(GL2.GL_QUADS);

        float x = -4f;
        float y = 4f;
        for (int c = 0; c < 4; c++) {
             gl.glColor3f(0f, 0f, 0.0f);
           //cuadros
            for (int b = 0; b < 2; b++) {
                for (float a = 0; a < 4; a++) {
                    gl.glVertex3f(x, y, 0);
                    x = x + 1f;
                    gl.glVertex3f(x, y, 0);
                    y = y - 1;
                    gl.glVertex3f(x, y, 0);
                    x = x - 1f;
                    gl.glVertex3f(x, y, 0);

                    x = x + 2f;
                    y = y + 1f;
                }
                y = y - 1f;
                x = -3f;
            }

                x =-4f;// sep_cada

        }
        gl.glEnd();

        gl.glColor3f(0, 0, 0);
        gl.glLineWidth(5);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex3f(-4, 4, 0);
        gl.glVertex3f(4, 4, 0);
        gl.glVertex3f(4, -4, 0);
        gl.glVertex3f(-4, -4, 0);
        gl.glEnd();
    }


    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {

    }
}
