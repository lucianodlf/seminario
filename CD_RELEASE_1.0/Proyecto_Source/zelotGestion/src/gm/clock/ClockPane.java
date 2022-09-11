/*
*****************************************************************************************
This file is part of Zelot Gestion ERP.

    Zelot Gestion ERP is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zelot Gestion ERP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zelot Gestion ERP.  If not, see <http://www.gnu.org/licenses/>.

*****************************************************************************************
Este archivo forma parte de Zelot Gestión ERP.

     Zelot Gestion ERP es un software libre: usted puede redistribuirlo y / o modificar
     bajo los términos de la GNU Lesser General Public License publicada por
     la Free Software Foundation, bien de la versión 3 de la Licencia, o
     (a su elección) cualquier versión posterior.

     Zelot Gestión ERP se distribuye con la esperanza de que sea útil,
     pero SIN NINGUNA GARANTÍA, incluso sin la garantía implícita de
     COMERCIALIZACIÓN o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. Consulte la
     GNU Lesser General Public License para más detalles.

     Debería haber recibido una copia de la GNU Lesser General Public License
     junto con Zelot Gestión ERP. Si no, vea <http://www.gnu.org/licenses/>.
******************************************************************************************/
package gm.clock;

import java.util.Date;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClockPane
    extends JPanel implements Runnable {

  private Thread timer = null;

  private RenderingHints render1 = new RenderingHints(RenderingHints.
      KEY_ALPHA_INTERPOLATION,
      RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
      );
  private RenderingHints render2 = new RenderingHints(RenderingHints.
      KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
  private RenderingHints render3 = new RenderingHints(RenderingHints.
      KEY_COLOR_RENDERING,
      RenderingHints.VALUE_COLOR_RENDER_QUALITY);
  private RenderingHints render4 = new RenderingHints(RenderingHints.
      KEY_DITHERING,
      RenderingHints.VALUE_DITHER_ENABLE
      );
  private RenderingHints render5 = new RenderingHints(RenderingHints.
      KEY_FRACTIONALMETRICS
      ,
      RenderingHints.VALUE_FRACTIONALMETRICS_ON);
  private RenderingHints render6 = new RenderingHints(RenderingHints.
      KEY_INTERPOLATION
      ,
      RenderingHints.VALUE_INTERPOLATION_BICUBIC);
  private RenderingHints render7 = new RenderingHints(RenderingHints.
      KEY_RENDERING
      ,
      RenderingHints.VALUE_RENDER_QUALITY);

  private RenderingHints render8 = new RenderingHints(RenderingHints.
      KEY_STROKE_CONTROL
      ,
      RenderingHints.VALUE_STROKE_PURE);
  private RenderingHints render9 = new RenderingHints(RenderingHints.
      KEY_TEXT_ANTIALIASING
      , RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

  private BasicStroke stroke0 = new BasicStroke(0.7f, BasicStroke.CAP_ROUND,
                                                BasicStroke.JOIN_ROUND);
  private BasicStroke stroke1 = new BasicStroke(1f, BasicStroke.CAP_ROUND,
                                                BasicStroke.JOIN_ROUND);
  private BasicStroke stroke2 = new BasicStroke(2f, BasicStroke.CAP_ROUND,
                                                BasicStroke.JOIN_ROUND);
  private BasicStroke stroke3 = new BasicStroke(3.5f, BasicStroke.CAP_ROUND,
                                                BasicStroke.JOIN_ROUND);

  private Ellipse2D.Double oval;

  private int sleep = 1000;

  private BufferedImage buffer;
  private Graphics2D g;

  private int s, m, h;
  private double xh, yh, xm, ym, xs, ys, xm1, xm2, ym1, ym2, mm, xcenter,ycenter;
  private Line2D.Double line;

  public ClockPane() {
    timer = new Thread(this);
    timer.start();

  }

  public void run() {
    while (timer != null) {
      try {
        Thread.sleep(sleep);
        repaint();
      }
      catch (InterruptedException e) {}

    }

    timer = null;
  }

  Date dat;

  @SuppressWarnings("deprecation")
private void updateClock(Graphics gg) {

    dat = new Date();
    s = dat.getSeconds();
    m = dat.getMinutes();
    h = dat.getHours();
    xcenter = 150 / 2;
    ycenter = 150 / 2;
    mm = 0;

    if (oval == null) {
      oval = new Ellipse2D.Double(xcenter - 3, ycenter - 3, 6, 6);
    }

    xs = (Math.cos(s * 3.14f / 30 - 3.14f / 2) * 50 + xcenter);
    ys = (Math.sin(s * 3.14f / 30 - 3.14f / 2) * 50 + ycenter);
    xm = (Math.cos(m * 3.14f / 30 - 3.14f / 2) * 40 + xcenter);
    ym = (Math.sin(m * 3.14f / 30 - 3.14f / 2) * 40 + ycenter);
    xh = (Math.cos( (h * 30 + m / 2) * 3.14f / 180 - 3.14f / 2) * 30 +
          xcenter);
    yh = (Math.sin( (h * 30 + m / 2) * 3.14f / 180 - 3.14f / 2) * 30 +
          ycenter);

    g.setColor(Color.darkGray);
    g.setStroke(stroke0);
    for (mm = 0; mm <= 60; mm++) {
      xm1 = (Math.cos(mm * 3.14f / 30 - 3.14f / 2) * 55 + xcenter);
      ym1 = (Math.sin(mm * 3.14f / 30 - 3.14f / 2) * 55 + ycenter);
      xm2 = (Math.cos(mm * 3.14f / 30 - 3.14f / 2) * 62 + xcenter);
      ym2 = (Math.sin(mm * 3.14f / 30 - 3.14f / 2) * 62 + ycenter);
      line = new Line2D.Double(xm1, ym1, xm2, ym2);
      g.draw(line);
    }
    g.setStroke(stroke2);
    for (mm = 0; mm <= 60; mm += 5) {
      xm1 = (Math.cos(mm * 3.14f / 30 - 3.14f / 2) * 54 + xcenter);
      ym1 = (Math.sin(mm * 3.14f / 30 - 3.14f / 2) * 54 + ycenter);
      xm2 = (Math.cos(mm * 3.14f / 30 - 3.14f / 2) * 62 + xcenter);
      ym2 = (Math.sin(mm * 3.14f / 30 - 3.14f / 2) * 62 + ycenter);
      line = new Line2D.Double(xm1, ym1, xm2, ym2);
      g.draw(line);
    }
    int counter = 1;
    for (mm = 5; mm <= 60; mm += 5) {
      xm1 = (Math.cos(mm * 3.14f / 30 - 3.14f / 2) * 46 + xcenter);
      ym1 = (Math.sin(mm * 3.14f / 30 - 3.14f / 2) * 46 + ycenter);
      if (mm < 60) {
        g.drawString("" + counter++, (int) xm1 - 3, (int) ym1 + 4);
      }
      else {
        g.drawString("" + counter++, (int) xm1 - 4, (int) ym1 + 4);
      }
    }

    g.setColor(Color.darkGray);
    g.setStroke(stroke3);
    line = new Line2D.Double(xcenter, ycenter, xh, yh);
    g.draw(line);
    g.setColor(Color.darkGray);
    g.setStroke(stroke2);
    line = new Line2D.Double(xcenter, ycenter, xm, ym);
    g.draw(line);

    g.setColor(Color.black);
    g.setStroke(stroke1);
    line = new Line2D.Double(xcenter, ycenter, xs, ys);
    g.draw(line);

    g.setColor(Color.lightGray);
    g.setStroke(stroke3);
    g.fill(oval);
    g.setColor(Color.gray);
    g.setStroke(stroke0);
    g.draw(oval);

  }

  public void paintComponent(Graphics gg) {
    if (buffer == null) {
      buffer = (BufferedImage) createImage(getWidth(), getHeight());
      g = buffer.createGraphics();
      g.addRenderingHints(render1);
      g.addRenderingHints(render2);
      g.addRenderingHints(render3);
      g.addRenderingHints(render4);
      g.addRenderingHints(render5);
      g.addRenderingHints(render6);
      g.addRenderingHints(render7);
      g.addRenderingHints(render8);
      g.addRenderingHints(render9);
      g.setFont(new Font("Dialog", Font.PLAIN, 10));

    }
    super.paintComponent(g);
    updateClock(g);
    gg.drawImage(buffer, 0, 0, getWidth(), getHeight(), this);
  }
}
