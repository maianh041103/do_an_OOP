
package home;

public class Slide2 extends javax.swing.JPanel {

    public Slide2() {
        initComponents();
    }
 
   
    private void initComponents() {

        pictureBox1 = new PictureBox();

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/slide2.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }


  
    private PictureBox pictureBox1;
    
}