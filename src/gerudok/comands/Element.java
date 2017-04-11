package gerudok.comands;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

import gerudok.view.painters.ElementPainter;



@SuppressWarnings("serial")
public abstract class Element implements Serializable {

	protected Paint paint;
	protected SerializableStrokeAdapter stroke;
	protected Color strokeColor;
	protected String name;
	protected String description;
	
protected ElementPainter elementPainter;
	
	
	
	//metoda za kloniranje elemenata
	abstract public Element clone();
	
	
	public Element(Stroke stroke, Paint paint,Color  strokeColor){
		setStroke(stroke);
		this.paint = paint;
		this.strokeColor=strokeColor;
	}

	/**Copy konstruktor klase

	 * 
	 */
	public Element(Element element){
		this.stroke=element.stroke;
		this.paint=element.paint;
		this.strokeColor=element.strokeColor;
		this.name=element.name;
		this.description=element.description;
		this.elementPainter=element.elementPainter;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ElementPainter getPainter() {
		return elementPainter;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = new SerializableStrokeAdapter(stroke);
	}

	public String toString(){
		return name;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}


	public ElementPainter getElementPainter() {
		return elementPainter;
	}


	public void setElementPainter(ElementPainter elementPainter) {
		this.elementPainter = elementPainter;
	}
}

