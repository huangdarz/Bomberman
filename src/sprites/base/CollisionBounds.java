package sprites.base;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public class CollisionBounds extends Bounds {
	
	protected CollisionBounds(double x, double y, double width, double height) {
		super(x, y, 0, Math.abs(width), Math.abs(height), 0);
	}

	@Override
	public boolean contains(Point2D p) {
		return p.getX() > getMinX() && p.getX() < getMaxX() && p.getY() > getMinY() && p.getY() < getMaxY();
	}

	@Override
	public boolean contains(Point3D p) {
		return p.getX() > getMinX() && p.getX() < getMaxX() && p.getY() > getMinY() && p.getY() < getMaxY();
	}

	@Override
	public boolean contains(Bounds b) {
		return b.getMinX() > getMinX() && b.getMaxX() < getMaxX() && b.getMinY() > getMinY() && b.getMaxY() < getMaxY();
	}

	@Override
	public boolean contains(double x, double y) {
		return x > getMinX() && x < getMaxX() && y > getMinY() && y < getMaxY();
	}

	@Override
	public boolean contains(double x, double y, double z) {
		return x > getMinX() && x < getMaxX() && y > getMinY() && y < getMaxY();
	}

	@Override
	public boolean contains(double x, double y, double width, double height) {
		return x > getMinX() && x+width < getMaxX() && y > getMinY() && y+height < getMaxY();
	}

	@Override
	public boolean contains(double x, double y, double z, double width, double height, double depth) {
		return x > getMinX() && x+width < getMaxX() && y > getMinY() && y+height < getMaxY();
	}

	@Override
	public boolean intersects(Bounds b) {
		return b.getMinX() > getMinX() || b.getMaxX() < getMaxX() || b.getMinY() > getMinY() || b.getMaxY() < getMaxY();
	}

	@Override
	public boolean intersects(double x, double y, double width, double height) {
		return x > getMinX() || x+width < getMaxX() || y > getMinY() || y+height < getMaxY();
	}

	@Override
	public boolean intersects(double x, double y, double z, double width, double height, double depth) {
		return x > getMinX() || x+width < getMaxX() || y > getMinY() || y+height < getMaxY();
	}

	@Override
	public boolean isEmpty() {
		return getWidth() < 0 || getHeight() < 0;
	}
	
	/**
	 * returns an anonymous ArrayList which contains the directions an object is touching
	 * first segment of the if statement checks the distance between one edge of this object 
	 * and the opposite edge of the other object is less than or equal to a set threshold 
	 * second segment checks the distance between one edge of this object and the same edge 
	 * of the other object is less than the height of this object 
	 * 
	 * if both of these conditions are met the direction checked will be added to the anonymous ArrayList
	 * 
	 * @param b The Collision Bounds for the Sprite checking
	 * @param threshold The threshold for the collision
	 * @return An anonymous ArrayList which contains the directions an object is touching
	 */
	@SuppressWarnings("serial")
	public synchronized ArrayList<Sprite.Direction> isTouching(CollisionBounds b, int threshold) {
		return new ArrayList<Sprite.Direction>() {{
			if(Math.abs(getMinX() - b.getMaxX()) <= threshold && Math.min(Math.abs(getMaxY() - b.getMaxY()), Math.abs(getMinY() - b.getMinY())) < getHeight()) add(Sprite.Direction.LEFT);
			if(Math.abs(-getMaxX() + b.getMinX()) <= threshold && Math.min(Math.abs(getMaxY() - b.getMaxY()), Math.abs(getMinY() - b.getMinY())) < getHeight()) add(Sprite.Direction.RIGHT);
			if(Math.abs(getMinY() - b.getMaxY()) <= threshold && Math.min(Math.abs(getMaxX() - b.getMaxX()), Math.abs(getMinX() - b.getMinX())) < getWidth()) add(Sprite.Direction.UP);
			if(Math.abs(-getMaxY() + b.getMinY()) <= threshold && Math.min(Math.abs(getMaxX() - b.getMaxX()), Math.abs(getMinX() - b.getMinX())) < getWidth()) add(Sprite.Direction.DOWN);
		}};
	}
}
