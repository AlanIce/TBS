package TBS.Model;
// Generated Jan 3, 2017 11:41:10 PM by Hibernate Tools 5.1.0.Beta1

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

	private String courseId;
	private String courseName;
	private String courseImgSrc;
	private String teacher;
	private boolean auto;
	private int questionNum;
	private String questionIdlist;

	public Course() {
	}

	public Course(String courseId, boolean auto, int questionNum) {
		this.courseId = courseId;
		this.auto = auto;
		this.questionNum = questionNum;
	}

	public Course(String courseId, String courseName, String courseImgSrc, String teacher, boolean auto,
			int questionNum, String questionIdlist) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseImgSrc = courseImgSrc;
		this.teacher = teacher;
		this.auto = auto;
		this.questionNum = questionNum;
		this.questionIdlist = questionIdlist;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseImgSrc() {
		return this.courseImgSrc;
	}

	public void setCourseImgSrc(String courseImgSrc) {
		this.courseImgSrc = courseImgSrc;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public boolean isAuto() {
		return this.auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

	public int getQuestionNum() {
		return this.questionNum;
	}

	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}

	public String getQuestionIdlist() {
		return this.questionIdlist;
	}

	public void setQuestionIdlist(String questionIdlist) {
		this.questionIdlist = questionIdlist;
	}

}
