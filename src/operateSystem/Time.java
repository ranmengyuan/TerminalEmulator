package operateSystem;

public class Time {

	private int year; 
	private int month; 
	private int date; 
	private int hour; 
	private int minute; 
	private int second;
	
	
	public Time() {
		super();
		this.year = 0;
		this.month = 0;
		this.date = 0;
		this.hour = 0;
		this.minute = 0;
		this.second = 0;
	}
	public Time(int year, int month, int date, int hour, int minute, int second) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	
}
