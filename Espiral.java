/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficacion;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
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
public class Espiral implements GLEventListener{
    public static void main(String[] args) {
        Frame frame= new Frame("Espiral.java");
        GLCanvas canvas= new GLCanvas();
        canvas.addGLEventListener(new Espiral());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator= new Animator(canvas);

        frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            // RunthisonanotherthreadthantheAWT eventqueueto
            // makesurethecalltoAnimator.stop() completes before
            // exiting
            new Thread(new Runnable() {

                public void run(){
                animator.stop();
                System.exit(0);
                }
            }).start();
        }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }
    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl= glad.getGL().getGL2();
        GLU glu= new GLU();
        gl.glClearColor(0,0,0,1);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        glu.gluOrtho2D (-5.0, 5.2, -5.0, 5.2);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
 
    @Override
    public void dispose(GLAutoDrawable glad) {
    }
    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl= glad.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glPointSize(1.0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        parametricas(gl);
        gl.glFlush();
}
   
    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        GL2 gl= glad.getGL().getGL2();
        gl.glViewport(0, 0, i2, i3);
    }
    void parametricas(GL2 gl){
        double x,tot=2*Math.PI,inc=tot/120;
        gl.glBegin(GL2.GL_LINE_LOOP);
 
        for(x=0;x<tot;x+=inc){
        gl.glVertex2d(x*Math.cos(tot*x),x*Math.sin(tot*x)-0.2);
        
        }
        
gl.glEnd();
}
}
