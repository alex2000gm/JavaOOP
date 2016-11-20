/**
 * Created by alex2000 on 20.11.16.
 */
public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }


    public Range intersection(Range range) {
        //пересечение
        if (this.from >= range.to || range.from >= this.to) {
            return null;
        }
        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    private boolean hasIntersection(Range range) {
        return from < range.to && range.from < to;
    }

    public Range[] union(Range range) {
        //объединение
        if (!hasIntersection(range)) {
            return new Range[]{this, range};
        }
        return new Range[]{new Range(Math.min(from, range.to), Math.max(to, range.to))};
    }

    public Range[] difference(Range range) {
        if (!hasIntersection(range)) {
            return new Range[]{this};
        } else if (this.to > range.from) {
            return new Range[]{new Range(this.from, range.from)};
        } else if (this.from < range.to) {
            return new Range[]{new Range(range.to, this.to)};
        } else if (this.from < range.from && this.to > range.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.from, this.to)};
        }
        return new Range[0];
    }
}