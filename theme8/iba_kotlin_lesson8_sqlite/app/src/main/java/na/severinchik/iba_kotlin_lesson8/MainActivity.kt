package na.severinchik.iba_kotlin_lesson8

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import na.severinchik.iba_kotlin_lesson8.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var list: RecyclerView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = NoteAdapter()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.list.adapter = adapter
        binding.addButton.setOnClickListener {
            AddDialogFragment().show(
                supportFragmentManager,
                "Dialog"
            )
        }
        binding.list.setOnClickListener {
            adapter.notifyDataSetChanged()
        }
        getDataFromDB().observe(this, Observer {
            it?.let {
                adapter.submitList(getDataFromDB().value)
                adapter.notifyDataSetChanged()
            }
        })


    }

    fun getDataFromDB(): LiveData<List<Note>> {
        var noteList: MutableLiveData<List<Note>> = MutableLiveData()
        var result = ArrayList<Note>()
        val database = OldDatabase.getInstance(this).readableDatabase
        val cursor = database.query(
            DatabaseInfo.TableNotesInfo.TABLE_NOTES_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            try {
                var uid = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))

                var name =
                    cursor.getString(cursor.getColumnIndex(DatabaseInfo.TableNotesInfo.COLUMN_NOTE_NAME))
                var info =
                    cursor.getString(cursor.getColumnIndex(DatabaseInfo.TableNotesInfo.COLUMN_NOTE_INFO))
                var time =
                    cursor.getString(cursor.getColumnIndex(DatabaseInfo.TableNotesInfo.COLUMN_TIME))
                result.add(Note(uid, name, info, time))
            } catch (e: Exception) {
                Log.d("DB", "getDataFromDB: ${e.message}")
            }
        }
        cursor.close()
        noteList.value=result
        return noteList
    }
}

data class Note(val uid: Int, val name: String, val info: String, val time: String)