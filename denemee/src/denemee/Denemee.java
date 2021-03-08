/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denemee;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author risin
 */
public class Denemee extends Application {
   Line saniye = new Line();
   Line dakika = new Line();
   Line saat = new Line ();
   Circle circle = new Circle();
   Text digital;
    
    @Override
    public void start(Stage primaryStage) {
        Group grup = new Group();    
         Timer timer = new Timer (true);//zamanlayıcımıza true degerini veriyoruz
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(80.0f);//dairenin yarıcapına 80 veriyoruz
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        circle.setStrokeWidth(4);
        
        saniye.setStartX(100.0f);
        saniye.setStartY(120.0f);//uzunlugunu belirlemek icin
        saniye.setEndX(100.0f);
        saniye.setEndY(30.0f);//uzunlugunu belirlemek icin
        saniye.setStroke(Color.RED);
        
        
         dakika.setStartX(100.0f);
         dakika.setStartY(110.0f);//uzunlugunu belirlemek icin
         dakika.setEndX(100.0f);
         dakika.setEndY(30.0f);//uzunlugunu belirlemek icin
         dakika.setStroke(Color.RED);
         dakika.setStrokeWidth(3);
        
        
        saat.setStartX(100.0f);
        saat.setStartY(110.0f);//uzunlugunu belirlemek icin
        saat.setEndX(100.0f);
        saat.setEndY(60.0f);//uzunlugunu belirlemek icin
        saat.setStroke(Color.RED);
        saat.setStrokeWidth(3);
        
        Text t1 = new Text(100.0f - 5.0, 100.0f - 80.0f + 12.0, "12");
        Text t2 = new Text(100.0f- 80.0f + 3.0, 100.0f + 5.0, "9");//burada dairenin icinde yazacak
        Text t3 = new Text(100.0f + 80.0f - 10.0, 100.0f + 3.0, "3");//rakamlarin yerlerini belirliyoruz
        Text t4 = new Text(100.0f- 3.0, 100.0f + 80.0f - 3.0, "6");
        digital=new Text(100.0f - 15.0, 100.0f + 90.0f + 12.0,java.time.LocalTime.now().getHour()+":"+java.time.LocalTime.now().getMinute());
        
        grup.getChildren().add(circle);
        grup.getChildren().add(saniye);//grubuzmuza dahil ediyoruz 
        grup.getChildren().add(dakika);
        grup.getChildren().add(saat);
        grup.getChildren().add(digital);
        
        grup.getChildren().add(t1);
        grup.getChildren().add(t2);
        grup.getChildren().add(t3);     //grubumuza dahil ediyoruz
        grup.getChildren().add(t4);
        Scene scene = new Scene(grup, 200, 250);//grubumuzu sahnemize baglıyoruz
        dondur();
       
        timer.scheduleAtFixedRate(new Operate (), 0, 1000);
        primaryStage.setTitle("Clock"); 
        primaryStage.setScene(scene);// cercemize baglıyotuz sahnemizi
        primaryStage.show(); 

    }
    class Operate extends TimerTask//saatimizi gercek zamanlı yapmak icin
	{
		
		public void run ()
		{
			
			Rotate rotate = new Rotate (6, 100.0f, 100.0f);
			Rotate rotate_hours = new Rotate (.5, 100.0f, 100.0f);
			saniye.getTransforms().add(rotate);
			
			if (java.time.LocalTime.now().getSecond() == 59)//her 60 saniyede bir donucekler
			{
				dakika.getTransforms().add(rotate);
				saat.getTransforms().add(rotate_hours);
                                digital.setText(java.time.LocalTime.now().getHour()+":"+java.time.LocalTime.now().getMinute());
                               
			}
		}
	}
      private void dondur()//burada akrep ve yelkovanın bulundugu saat degerini donduruyoruz
	{
		int hour = java.time.LocalTime.now().getHour();
		int minute = java.time.LocalTime.now().getMinute();//saat dakika ve saniye
		int second = java.time.LocalTime.now().getSecond();//bilgilerini aliyoruz
		
		double seconds_degree = second * 6;//saniyeyi derece ceviriyoruz
		double minutes_degree = minute * 6;//dakikayi derece ceviriyoruz
		double hours_degree = hour * 30 + minute* .5;//saati derece ceviriyoruz
		
		Rotate rotate_seconds = new Rotate (seconds_degree, 100.0f, 100.0f);
		Rotate rotate_minutes = new Rotate (minutes_degree, 100.0f, 100.0f);//dondurme derecelerini
		Rotate rotate_hours = new Rotate (hours_degree, 100.0f, 100.0f);//belirliyoruz
		
		saniye.getTransforms().add(rotate_seconds);
		dakika.getTransforms().add(rotate_minutes);//dondurme ıslemini gerceklestiriyoruz
		saat.getTransforms().add(rotate_hours);
                
              
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
