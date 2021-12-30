package fr.epsi.epsiprojetmodule

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonStudentList:Button = findViewById(R.id.buttonStudentList)
        val buttonMarketShelf:Button = findViewById(R.id.buttonMarketShelf)

        buttonStudentList.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,StudentsActivity::class.java)
            newIntent.putExtra("Title",getString(R.string.txt_student_name))
            newIntent.putExtra("urlImage","https://media.istockphoto.com/vectors/man-in-the-shirt-and-tie-businessman-avatar-or-male-face-icon-vector-vector-id1173599830?k=20&m=1173599830&s=170667a&w=0&h=f-xN6U2zWwcLcQw68Y5OLqL4VvStOp-8PFLE9TTHmqA=")
            newIntent.putExtra("Name",getString(R.string.txt_student_name)+ " " + getString(R.string.txt_student_nickname))
            newIntent.putExtra("Email",getString(R.string.txt_student_email))
            newIntent.putExtra("Groupe",getString(R.string.txt_student_group))
            newIntent.putExtra("Link",getString(R.string.txt_student_link))
            startActivity(newIntent)
        })

        buttonMarketShelf.setOnClickListener(View.OnClickListener {
            val newIntent= Intent(application,ShelfActivity::class.java)
            startActivity(newIntent)
        })
    }
}