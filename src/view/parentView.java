
package view;

import controller.parentController;
import home.homeView;
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import menu.Header;
import menu.Menu;
import menu.MenuEvent;
import scroll.ScrollPaneWin11;

public class parentView extends JFrame{
    private JPanel pnFrame,pnTable;
    private ScrollPaneWin11 scroll ;
    private Menu menu;
    private Header pnHeader;
    private tableMonView TableMonView;
    private tableLopQLView TableLopQLView;
    private tableLopHPView TableLopHPView;
    private findStudentView FindStudentView;
    private findLopQLView FindLopQLView;
    private findLopHPView FindLopHPView;
    private tableChuongTrinhKhungView TableChuongTrinhKhungView;
    private homeView HomeView;
    private thongKeLopQLView ThongKeLopQLView;
    private doThiTBMonView dothiTBMon;
    private doThiDiemTBSvView doThiDiemTBofSV;
    private doThiSoSVTruotView doThiSoSVTruot;
    public parentController ParentController = new parentController(this);
    
    public parentView(){
        init();
        this.setVisible(true);
    }
    
    public void init(){
        setUpViewFrame();
        setUpViewMenu();
        setUpViewHeader();
        
        setUpViewTableMon();
        setUpViewTableLopQL();
        setUpViewTableLopHP();
        setUpViewFindSV();
        setUpViewFindLopQL();
        setUpViewFindLopHP();
        setUpViewDoThiTBMon();
        setUpViewDoThiTBofSV();
        setUpViewdoThiSoSVTruot();
        setUpViewChuongTrinhKhung();
        setUpViewHome();
        setUpViewThongKeLopQL();
        showForm(HomeView);
       
        addAction();
    }
    
    public void setUpViewFrame(){
        this.setSize(1300,750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        pnFrame = new JPanel();
        pnFrame.setBackground(new Color(250,250,250));
        pnFrame.setBounds(0, 0, getWidth() - 15, getHeight() - 35);
        pnFrame.setLayout(null);
        
        pnTable = new JPanel();
        pnTable.setBackground(new Color(250,250,250));
        pnTable.setBounds(180,75,1120,getHeight()-75);
        pnTable.setLayout(null);
        
        pnFrame.add(pnTable);
        this.add(pnFrame); 
    }
    
    public void setUpViewHeader(){
        pnHeader = new Header();
        pnHeader.setLayout(null);
        pnHeader.setBorder(null);
        pnHeader.setBounds(0,0,getWidth(),75);
        pnFrame.add(pnHeader);
    }
    
    public void setUpViewMenu(){
        menu = new Menu();
        scroll = new ScrollPaneWin11();
        scroll.setBounds(0, 75, 180,  getHeight()-75);
        scroll.setViewportView(menu);
        pnFrame.add(scroll);
    }
   
    public void setUpViewTableMon(){
        TableMonView = new tableMonView();   
        TableMonView.setBounds(0,0,1120,getHeight()-75);
        TableMonView.getBtnInsert().addActionListener(ParentController);  
        TableMonView.getTableMonController().getDataMon();
    }  
    
    public void setUpViewTableLopQL(){
        TableLopQLView = new tableLopQLView();
        TableLopQLView.setBounds(0,0,1120,getHeight()-75);
        TableLopQLView.getBtnInsert().addActionListener(ParentController);
    }
    
    public void setUpViewTableLopHP(){
        TableLopHPView = new tableLopHPView();
        TableLopHPView.setBounds(0, 0, 1120, getHeight()-75);
        TableLopHPView.getBtnInsert().addActionListener(ParentController);
        TableLopHPView.getTableLopHPController().getDataLopHP();
    }
    
    public void setUpViewFindSV(){
        FindStudentView = new findStudentView();
        FindStudentView.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewFindLopQL(){
        FindLopQLView = new findLopQLView();
        FindLopQLView.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewFindLopHP(){
        FindLopHPView = new findLopHPView();
        FindLopHPView.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewHome(){
        HomeView = new homeView();
        HomeView.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewChuongTrinhKhung(){
        TableChuongTrinhKhungView = new tableChuongTrinhKhungView();
        TableChuongTrinhKhungView.setBounds(0, 0, 1120, getHeight()-75); 
    }
    
    public void setUpViewThongKeLopQL(){
        ThongKeLopQLView = new thongKeLopQLView();
        ThongKeLopQLView.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewDoThiTBMon(){
        dothiTBMon = new doThiTBMonView();
        dothiTBMon.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewDoThiTBofSV(){
        doThiDiemTBofSV = new doThiDiemTBSvView();
        doThiDiemTBofSV.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void setUpViewdoThiSoSVTruot() {
        doThiSoSVTruot = new doThiSoSVTruotView();
        doThiSoSVTruot.setBounds(0, 0, 1120, getHeight()-75);
    }
    
    public void resetFind(){
        FindLopHPView.removeAll();
        FindLopHPView.setUpViewPanel();
        FindLopHPView.setUpViewTitle();
        FindLopHPView.setUpViewFind();
        FindLopHPView.setUpButton();
        
        FindLopQLView.removeAll();
        FindLopQLView.setUpViewPanel();
        FindLopQLView.setUpViewTitle();
        FindLopQLView.setUpViewFind();
        FindLopQLView.setUpButton();
        
        FindStudentView.removeAll();
        FindStudentView.setUpViewPanel();
        FindStudentView.setUpViewTitle();
        FindStudentView.setUpViewFind();
        FindStudentView.setUpButton();
    }
    
    public void addAction(){
        menu.setEvent(new MenuEvent(){
            @Override
            public void selected(int index, int subIndex) {
                if(index == 0&& subIndex == 0){
                    resetFind();
                    showForm(HomeView);
                }
                else if(index == 1&& subIndex == 1){
                    resetFind();
                    showForm(TableMonView);
                }
                else if(index == 1 && subIndex == 2){
                    resetFind();
                    showForm(TableLopHPView);
                }
                else if(index == 1 && subIndex == 3){
                    resetFind();
                    showForm(TableLopQLView);
                }
                else if(index == 4 && subIndex == 1){
                    resetFind();
                    showForm(FindStudentView);
                }
                else if(index == 4 && subIndex == 2){
                    resetFind();
                    showForm(FindLopHPView);
                }
                else if(index == 4 && subIndex == 3){
                    resetFind();
                    showForm(FindLopQLView);
                }
                else if(index == 2 && subIndex == 1){
                    resetFind();
                    showForm(dothiTBMon);
                }
                else if(index == 2 && subIndex == 2){
                    resetFind();
                    showForm(doThiDiemTBofSV);
                }
                else if(index == 2 && subIndex == 3) {
                    resetFind();
                    showForm(doThiSoSVTruot);
                }else if(index == 2 && subIndex == 4){
                    resetFind();
                    showForm(ThongKeLopQLView);
                }
                else if(index == 3 && subIndex == 0){
                    resetFind();
                    showForm(TableChuongTrinhKhungView);
                }
            }
        });
        
    }
    
    private void showForm(Component com) {
        pnTable.removeAll();
        pnTable.add(com);
        pnTable.repaint();
        pnTable.revalidate();
    }

    

    public JPanel getPnFrame() {
        return pnFrame;
    }

    public void setPnFrame(JPanel pnFrame) {
        this.pnFrame = pnFrame;
    }

    public ScrollPaneWin11 getScroll() {
        return scroll;
    }

    public void setScroll(ScrollPaneWin11 scroll) {
        this.scroll = scroll;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Header getPnHeader() {
        return pnHeader;
    }

    public void setPnHeader(Header pnHeader) {
        this.pnHeader = pnHeader;
    }

    public tableMonView getTableMonView() {
        return TableMonView;
    }

    public void setTableMonView(tableMonView TableMonView) {
        this.TableMonView = TableMonView;
    }
    
    
    
}
