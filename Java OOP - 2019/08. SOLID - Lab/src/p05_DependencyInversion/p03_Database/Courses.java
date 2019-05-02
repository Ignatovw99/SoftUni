package p05_DependencyInversion.p03_Database;

public class Courses {
    private Data database;

    public Courses(Data database) {
        this.database = database;
    }

    public void setDatabase(Data database) {
        this.database = database;
    }

    public void printAll()
    {
        Iterable<String> courses = this.database.courseNames();

        //print courses
    }

    public void printIds(Data database)
    {
        Iterable<Integer>coursesIds = database.courseIds();

        //print course ids
    }

    public void printById(int id)
    {
        Data database = new Data();
        String course = database.getCourseById(id);

        // print course
    }

    public void search(String substring)
    {
        Data database = new Data();
        Iterable<String> courses = database.search(substring);

        // print courses
    }
}
