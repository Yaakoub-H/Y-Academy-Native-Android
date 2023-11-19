    package com.example.quizapplication.model;

    import static android.content.ContentValues.TAG;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;

    import com.example.quizapplication.model.Course;
    import com.example.quizapplication.model.User;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;

    public class DatabaseHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "Y_Academy123.db";
        private static final int DATABASE_VERSION = 6;

        // Table names
        public static final String TABLE_COURSE = "Course";
        public static final String TABLE_USER = "User";

        // Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_PASSWORD_HASH = "passwordHash";

        // Course table column names
        private static final String KEY_COURSE_NAME = "courseName";
        private static final String KEY_CATEGORY = "category";
        private static final String KEY_PRICE = "price";
        private static final String KEY_RATE = "rate";
        private static final String KEY_STUDENT_COUNT = "studentCount";
        private static final String KEY_IS_POPULAR = "isPopular";
        private static final String KEY_USER_ID_COURSE = "userId";

        // User table column names
        private static final String KEY_FULL_NAME = "fullName";
        private static final String KEY_NICKNAME = "nickName";
        private static final String KEY_DATE_OF_BIRTH = "dateOfBirth";
        private static final String KEY_EMAIL = "email";
        private static final String KEY_GENDER = "gender";

        // Add new columns for ExtendedCourse
        private static final String KEY_NUMBER_OF_CLASSES = "numberOfClasses";
        private static final String KEY_NUMBER_OF_HOURS = "numberOfHours";
        private static final String KEY_ABOUT = "about";
        private static final String KEY_INSTRUCTOR_NAME = "instructorName";
        private static final String KEY_DIFFICULTY_LEVEL = "difficultyLevel";
        private static final String KEY_NUMBER_OF_QUIZZES = "numberOfQuizzes";

        private static final String TABLE_EXTENDED_COURSE = "ExtendedCourse";


        // SQL statements to create tables
        private static final String CREATE_TABLE_COURSE = "CREATE TABLE " + TABLE_COURSE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_COURSE_NAME + " TEXT," +
                KEY_CATEGORY + " TEXT," +
                KEY_PRICE + " REAL," +
                KEY_RATE + " REAL," +
                KEY_STUDENT_COUNT + " INTEGER," +
                KEY_IS_POPULAR + " INTEGER" +
                ");";


        private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_FULL_NAME + " TEXT," +
                KEY_NICKNAME + " TEXT," +
                KEY_DATE_OF_BIRTH + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_GENDER + " TEXT," +
                KEY_PASSWORD_HASH + " TEXT" +  // Add this line for the passwordHash column
                ");";

        private static final String CREATE_TABLE_EXTENDED_COURSE = "CREATE TABLE " + TABLE_EXTENDED_COURSE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NUMBER_OF_CLASSES + " INTEGER," +
                KEY_NUMBER_OF_HOURS + " INTEGER," +
                KEY_ABOUT + " TEXT," +
                KEY_INSTRUCTOR_NAME + " TEXT," +
                KEY_DIFFICULTY_LEVEL + " TEXT," +
                KEY_NUMBER_OF_QUIZZES + " INTEGER," +
                "FOREIGN KEY (" + KEY_ID + ") REFERENCES " + TABLE_COURSE + "(" + KEY_ID + ")" +
                ");";



        private static final String CREATE_TABLE_USER_COURSE_REGISTRATION = "CREATE TABLE UserCourseRegistration (" +
                "registrationId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," +
                "courseId INTEGER," +
                "FOREIGN KEY (userId) REFERENCES " + TABLE_USER + "(" + KEY_ID + ")," +
                "FOREIGN KEY (courseId) REFERENCES " + TABLE_COURSE + "(" + KEY_ID + ")" +
                ");";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create tables
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_COURSE);
            db.execSQL(CREATE_TABLE_USER_COURSE_REGISTRATION);
            db.execSQL(CREATE_TABLE_EXTENDED_COURSE);

        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
            db.execSQL("DROP TABLE IF EXISTS " + "ExtendedCourse");
            db.execSQL("DROP TABLE IF EXISTS UserCourseRegistration");
            onCreate(db);
        }


        public void addExtendedCourses(List<ExtendedCourse> extendedCourseList) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            for (ExtendedCourse extendedCourse : extendedCourseList) {
                // Insert values into ExtendedCourse table
                values.put(KEY_ID, extendedCourse.getCourseId());
                values.put(KEY_NUMBER_OF_CLASSES, extendedCourse.getNumberOfClasses());
                values.put(KEY_NUMBER_OF_HOURS, extendedCourse.getNumberOfHours());
                values.put(KEY_ABOUT, extendedCourse.getAbout());
                values.put(KEY_INSTRUCTOR_NAME, extendedCourse.getInstructorName());
                values.put(KEY_DIFFICULTY_LEVEL, extendedCourse.getDifficultyLevel());
                values.put(KEY_NUMBER_OF_QUIZZES, extendedCourse.getNumberOfQuizzes());

                db.insert(TABLE_EXTENDED_COURSE, null, values);
                values.clear();
            }

            // Close the database connection
            db.close();
        }


        public List<Course> searchCoursesByTitle(String title) {
            List<Course> courses = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String[] columns = {
                    KEY_ID,
                    KEY_COURSE_NAME,
                    KEY_CATEGORY,
                    KEY_PRICE,
                    KEY_RATE,
                    KEY_STUDENT_COUNT,
                    KEY_IS_POPULAR
            };

            String selection = KEY_COURSE_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + title + "%"};
            Cursor cursor = db.query(TABLE_COURSE, columns, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    courses.add(cursorToCourse(cursor));
                } while (cursor.moveToNext());
            }

            // Close the cursor and database
            cursor.close();
            db.close();

            return courses;
        }

        public List<Course> searchPopularCoursesByTitle(String title) {
            List<Course> courses = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String[] columns = {
                    KEY_ID,
                    KEY_COURSE_NAME,
                    KEY_CATEGORY,
                    KEY_PRICE,
                    KEY_RATE,
                    KEY_STUDENT_COUNT,
                    KEY_IS_POPULAR
            };

            String selection = KEY_IS_POPULAR + "=? AND " + KEY_COURSE_NAME + " LIKE ?";
            String[] selectionArgs = {"1", "%" + title + "%"};

            Cursor cursor = db.query(TABLE_COURSE, columns, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    courses.add(cursorToCourse(cursor));
                } while (cursor.moveToNext());
            }

            // Close the cursor and database
            cursor.close();
            db.close();

            return courses;
        }


        public Course getCourseById(int courseId) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(
                    TABLE_COURSE,
                    null,
                    KEY_ID + "=? AND " + KEY_IS_POPULAR + "=?",
                    new String[]{String.valueOf(courseId), "1"},
                    null,
                    null,
                    null
            );

            Course course = null;

            if (cursor != null && cursor.moveToFirst()) {

                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_COURSE_NAME);
                int categoryIndex = cursor.getColumnIndex(KEY_CATEGORY);
                int priceIndex = cursor.getColumnIndex(KEY_PRICE);
                int rateIndex = cursor.getColumnIndex(KEY_RATE);


                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String category = cursor.getString(categoryIndex);
                double price = cursor.getDouble(priceIndex);
                double rate = cursor.getDouble(rateIndex);

                course = new Course(id, name, category, price, rate);

                cursor.close();
            }

            db.close();

            return course;
        }





        public List<Course> getPopularCourses() {
            List<Course> popularCourses = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String[] columns = {
                    KEY_ID,
                    KEY_COURSE_NAME,
                    KEY_CATEGORY,
                    KEY_PRICE,
                    KEY_RATE,
                    KEY_STUDENT_COUNT,
                    KEY_IS_POPULAR
            };

            String selection = KEY_IS_POPULAR + "=?";
            String[] selectionArgs = {"1"};
            Cursor cursor = db.query(TABLE_COURSE, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    popularCourses.add(cursorToCourse(cursor));
                } while (cursor.moveToNext());
                cursor.close();
            }
            db.close();

            return popularCourses;
        }

        public List<ExtendedCourse> getAllExtendedCourses() {
            List<ExtendedCourse> extendedCourses = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String[] columns = {
                    KEY_ID,
                    KEY_NUMBER_OF_CLASSES,
                    KEY_NUMBER_OF_HOURS,
                    KEY_ABOUT,
                    KEY_INSTRUCTOR_NAME,
                    KEY_DIFFICULTY_LEVEL,
                    KEY_NUMBER_OF_QUIZZES
            };

            Cursor cursor = db.query(TABLE_EXTENDED_COURSE, columns, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    extendedCourses.add(cursorToExtendedCourse(cursor));
                } while (cursor.moveToNext());
                cursor.close();
            }

            db.close();
            return extendedCourses;
        }

        private ExtendedCourse cursorToExtendedCourse(Cursor cursor) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int numberOfClassesIndex = cursor.getColumnIndex(KEY_NUMBER_OF_CLASSES);
            int numberOfHoursIndex = cursor.getColumnIndex(KEY_NUMBER_OF_HOURS);
            int aboutIndex = cursor.getColumnIndex(KEY_ABOUT);
            int instructorNameIndex = cursor.getColumnIndex(KEY_INSTRUCTOR_NAME);
            int difficultyLevelIndex = cursor.getColumnIndex(KEY_DIFFICULTY_LEVEL);
            int numberOfQuizzesIndex = cursor.getColumnIndex(KEY_NUMBER_OF_QUIZZES);

            if (idIndex >= 0 && numberOfClassesIndex >= 0 && numberOfHoursIndex >= 0
                    && aboutIndex >= 0 && instructorNameIndex >= 0 && difficultyLevelIndex >= 0
                    && numberOfQuizzesIndex >= 0) {
                return new ExtendedCourse(
                        cursor.getInt(idIndex),
                        cursor.getInt(numberOfClassesIndex),
                        cursor.getInt(numberOfHoursIndex),
                        cursor.getString(aboutIndex),
                        cursor.getString(instructorNameIndex),
                        cursor.getString(difficultyLevelIndex),
                        cursor.getInt(numberOfQuizzesIndex)
                );
            }
            return null;
        }



        public void addCourses(List<Course> courses) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            for (Course course : courses) {
                values.clear(); // Clear the content values for each iteration

                values.put(KEY_COURSE_NAME, course.getCourseName());
                values.put(KEY_CATEGORY, course.getCategory());
                values.put(KEY_PRICE, course.getPrice());
                values.put(KEY_RATE, course.getRate());
                values.put(KEY_STUDENT_COUNT, course.getStudentCount());
                values.put(KEY_IS_POPULAR, course.isPopular() ? 1 : 0);

                // Insert the course into the TABLE_COURSE
                db.insert(TABLE_COURSE, null, values);
            }

            db.close();
        }

        public long addCourse(Course course) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_COURSE_NAME, course.getCourseName());
            values.put(KEY_CATEGORY, course.getCategory());
            values.put(KEY_PRICE, course.getPrice());
            values.put(KEY_RATE, course.getRate());
            values.put(KEY_STUDENT_COUNT, course.getStudentCount());
            values.put(KEY_IS_POPULAR, course.isPopular() ? 1 : 0);

            long courseId = db.insert(TABLE_COURSE, null, values);
            db.close();
            return courseId;
        }
        public void deleteCourse(long courseId) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_COURSE, KEY_ID + "=?", new String[]{String.valueOf(courseId)});
            db.close();
        }
        public Course getCourse(long courseId) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_COURSE, null, KEY_ID + "=?",
                    new String[]{String.valueOf(courseId)}, null, null, null, null);

            Course course = null;
            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int courseNameIndex = cursor.getColumnIndex(KEY_COURSE_NAME);
                int categoryIndex = cursor.getColumnIndex(KEY_CATEGORY);
                int priceIndex = cursor.getColumnIndex(KEY_PRICE);
                int rateIndex = cursor.getColumnIndex(KEY_RATE);
                int studentCountIndex = cursor.getColumnIndex(KEY_STUDENT_COUNT);
                int isPopularIndex = cursor.getColumnIndex(KEY_IS_POPULAR);

                if (idIndex >= 0  && courseNameIndex >= 0 && categoryIndex >= 0
                        && priceIndex >= 0 && rateIndex >= 0 && studentCountIndex >= 0 && isPopularIndex >= 0) {
                    course = new Course(
                            cursor.getLong(idIndex),
                            cursor.getString(courseNameIndex),
                            cursor.getString(categoryIndex),
                            cursor.getDouble(priceIndex),
                            cursor.getDouble(rateIndex),
                            cursor.getInt(studentCountIndex),
                            cursor.getInt(isPopularIndex) == 1
                    );
                }
                cursor.close();
            }

            db.close();
            return course;
        }


        public long addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_FULL_NAME, user.getFullName());
            values.put(KEY_PASSWORD_HASH, user.getPasswordHash());


            long userId = db.insert(TABLE_USER, null, values);

            db.close();

            return userId;
        }

        public boolean checkUserExists(String username) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID}, KEY_FULL_NAME + "=?",
                    new String[]{username}, null, null, null, null);

            boolean userExists = cursor != null && cursor.getCount() > 0;

            if (cursor != null) {
                cursor.close();
            }

            db.close();

            return userExists;
        }


        public boolean verifyLoginCredentials(String username, String passwordHash) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID}, KEY_FULL_NAME + "=? AND " + KEY_PASSWORD_HASH + "=?",
                    new String[]{username, passwordHash}, null, null, null, null);

            boolean credentialsValid = cursor != null && cursor.getCount() > 0;

            if (cursor != null) {
                cursor.close();
            }

            db.close();

            return credentialsValid;
        }



        public long updateUserProfile(User user) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_FULL_NAME, user.getFullName());
            values.put(KEY_NICKNAME, user.getNickName());
            values.put(KEY_DATE_OF_BIRTH, user.getDateOfBirth());
            values.put(KEY_EMAIL, user.getEmail());
            values.put(KEY_GENDER, user.getGender());

            long userId = db.insert(TABLE_USER, null, values);
            db.close();
            return userId;
        }
        public void deleteUser(long userId) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_USER, KEY_ID + "=?", new String[]{String.valueOf(userId)});
            db.close();
        }

        // Add this method to your DatabaseHelper class
        public long getUserIdByUsername(String username) {
            SQLiteDatabase db = this.getReadableDatabase();
            long userId = -1;

            Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID}, KEY_FULL_NAME + "=?",
                    new String[]{username}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int userIdColumnIndex = cursor.getColumnIndex(KEY_ID);
                if (userIdColumnIndex != -1) {
                    userId = cursor.getLong(userIdColumnIndex);
                }
                cursor.close();
            }

            db.close();
            return userId;
        }

        public boolean updateUserData(long userId, String username, String gender, String email, String nickName) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            // Add the new values to update
            contentValues.put("username", username);
            contentValues.put("gender", gender);
            contentValues.put("email", email);
            contentValues.put("nickname", nickName);

            String selection = "id = ?";
            String[] selectionArgs = {String.valueOf(userId)};

            // Use the correct table name here
            String tableName = "User";

            int rowsAffected = db.update(tableName, contentValues, selection, selectionArgs);
            db.close();

            return rowsAffected > 0;
        }


        public User getUser(long userId) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USER, null, KEY_ID + "=?",
                    new String[]{String.valueOf(userId)}, null, null, null, null);

            User user = null;

            if (cursor != null && cursor.moveToFirst()) {
                int idColumnIndex = cursor.getColumnIndex(KEY_ID);
                int fullNameColumnIndex = cursor.getColumnIndex(KEY_FULL_NAME);
                int nicknameColumnIndex = cursor.getColumnIndex(KEY_NICKNAME);
                int dateOfBirthColumnIndex = cursor.getColumnIndex(KEY_DATE_OF_BIRTH);
                int emailColumnIndex = cursor.getColumnIndex(KEY_EMAIL);
                int genderColumnIndex = cursor.getColumnIndex(KEY_GENDER);
                int passwordHashColumnIndex = cursor.getColumnIndex(KEY_PASSWORD_HASH);

                if (idColumnIndex != -1 && fullNameColumnIndex != -1
                        && nicknameColumnIndex != -1
                        && dateOfBirthColumnIndex != -1
                        && emailColumnIndex != -1
                        && genderColumnIndex != -1
                        && passwordHashColumnIndex != -1) {
                    user = new User(
                            cursor.getLong(idColumnIndex),
                            cursor.getString(fullNameColumnIndex),
                            cursor.getString(nicknameColumnIndex),
                            cursor.getString(dateOfBirthColumnIndex),
                            cursor.getString(emailColumnIndex),
                            cursor.getString(genderColumnIndex),
                            cursor.getString(passwordHashColumnIndex)
                    );
                }
                cursor.close();
            }

            db.close();
            return user;
        }

        private Course cursorToCourse(Cursor cursor) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int courseNameIndex = cursor.getColumnIndex(KEY_COURSE_NAME);
            int categoryIndex = cursor.getColumnIndex(KEY_CATEGORY);
            int priceIndex = cursor.getColumnIndex(KEY_PRICE);
            int rateIndex = cursor.getColumnIndex(KEY_RATE);
            int studentCountIndex = cursor.getColumnIndex(KEY_STUDENT_COUNT);
            int isPopularIndex = cursor.getColumnIndex(KEY_IS_POPULAR);

            if (idIndex >= 0  && courseNameIndex >= 0 && categoryIndex >= 0
                    && priceIndex >= 0 && rateIndex >= 0 && studentCountIndex >= 0 && isPopularIndex >= 0) {
                return new Course(
                        cursor.getLong(idIndex),
                        cursor.getString(courseNameIndex),
                        cursor.getString(categoryIndex),
                        cursor.getDouble(priceIndex),
                        cursor.getDouble(rateIndex),
                        cursor.getInt(studentCountIndex),
                        cursor.getInt(isPopularIndex) == 1
                );
            }
            return null;
        }

        public void registerUserForCourse(long userId, long courseId) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("userId", userId);
            values.put("courseId", courseId);
            long registrationId = db.insert("UserCourseRegistration", null, values);
            db.close();
        }

        public List<Course> getAllCourses() {
            List<Course> courseList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_COURSE, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    courseList.add(cursorToCourse(cursor));
                } while (cursor.moveToNext());
                cursor.close();
            }

            db.close();
            return courseList;
        }

        public List<Course> getCoursesByCategory(String category) {
            List<Course> courseList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_COURSE, null, KEY_CATEGORY + "=?", new String[]{category}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    courseList.add(cursorToCourse(cursor));
                } while (cursor.moveToNext());
                cursor.close();
            }

            db.close();
            return courseList;
        }

        public List<Course> getPopularCoursesByCategory(String category) {
            List<Course> popularCourseList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String selection = KEY_CATEGORY + "=? AND " + KEY_IS_POPULAR + "=?";
            String[] selectionArgs = {category, "1"};

            Cursor cursor = db.query(TABLE_COURSE, null, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    popularCourseList.add(cursorToCourse(cursor));
                } while (cursor.moveToNext());
                cursor.close();
            }

            db.close();

            // Sort the popular courses alphabetically by course name
            Collections.sort(popularCourseList, new Comparator<Course>() {
                @Override
                public int compare(Course course1, Course course2) {
                    return course1.getCourseName().compareToIgnoreCase(course2.getCourseName());
                }
            });

            return popularCourseList;
        }


        public List<Course> get3DDesignCourses() {
            return getCoursesByCategory("3D Design");
        }

        public List<Course> getGraphicDesignCourses() {
            return getCoursesByCategory("Graphic Design");
        }

        public List<Course> getWebDevelopmentCourses() {
            return getCoursesByCategory("Web Development");
        }

        public List<Course> getSEOMarketingCourses() {
            return getCoursesByCategory("SEO & Marketing");
        }

        public List<Course> getFinanceAccountingCourses() {
            return getCoursesByCategory("Finance & Accounting");
        }

        public List<Course> getPersonalDevelopmentCourses() {
            return getCoursesByCategory("Personal Development");
        }

        public List<Course> getOfficeProductivityCourses() {
            return getCoursesByCategory("Office Productivity");
        }

        public List<Course> getHRManagementCourses() {
            return getCoursesByCategory("HR Management");
        }





    }
