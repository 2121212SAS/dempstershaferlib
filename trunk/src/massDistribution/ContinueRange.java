package massDistribution;

import interfaces.IRange;

@SuppressWarnings("unchecked")
public class ContinueRange implements IRange {

	private final static MeasureType type = MeasureType.CONTINUOUS;

	private Comparable lowerBound;
	private Comparable upperBound;
	boolean openedLeft;
	boolean openedRight;

	/**
	 * Creates a range (<code>lowerBound</code>, <code>upperBound</code>). If
	 * openedLeft== true then the range excludes the <code>lowerBound</code>
	 * otherwise (openedLeft== true) the range includes <code>lowerBound</code>.
	 * The same happens also with the field <code>openedRight</code>.
	 * 
	 * @param lowerBound
	 *            : the left value of the range.
	 * @param upperBound
	 *            : the right value of the range.
	 * @param openedLeft
	 *            : <code>true</code> if the range is left opened, false if it
	 *            is left closed.
	 * @param openedRight
	 *            : <code>true</code> if the range is right opened, false if it
	 *            is right closed.
	 */
	public ContinueRange(Comparable lowerBound, Comparable upperBound,
			boolean openedLeft, boolean openedRight) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.openedLeft = openedLeft;
		this.openedRight = openedRight;
	}

	/**
	 * @return "the type of the range. "CONTINUE" in this case.
	 */
	public MeasureType getType() {
		return type;
	}

	/**
	 * @return the lowerBound
	 */
	public Comparable getLowerBound() {
		return lowerBound;
	}

	/**
	 * @param lowerBound
	 *            the lowerBound to set
	 */
	public void setLowerBound(Comparable lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the upperBound
	 */
	public Comparable getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(Comparable upperBound) {
		this.upperBound = upperBound;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsValue(Object value) {
		Comparable comparableValue = (Comparable) value;
		if (openedLeft && openedRight) {
			// (a,b)
			if (comparableValue.compareTo(lowerBound) > 0
					&& comparableValue.compareTo(upperBound) < 0)
				return true;

		} else if (openedLeft && !openedRight) {
			// (a,b]
			if (comparableValue.compareTo(lowerBound) > 0
					&& comparableValue.compareTo(upperBound) <= 0)
				return true;

		} else if (!openedLeft && openedRight) {
			// [a,b)
			if (comparableValue.compareTo(lowerBound) >= 0
					&& comparableValue.compareTo(upperBound) < 0)
				return true;

		} else if (!openedLeft && !openedRight) {
			// [a,b]
			if (comparableValue.compareTo(lowerBound) >= 0
					&& comparableValue.compareTo(upperBound) <= 0)
				return true;
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String leftBrace;
		String rightBrace;

		if (openedLeft)
			leftBrace = "(";
		else
			leftBrace = "[";
		if (openedRight)
			rightBrace = ")";
		else
			rightBrace = "]";
		return leftBrace + lowerBound + ", " + upperBound + rightBrace;
	}

}