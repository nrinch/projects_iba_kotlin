package na.severinchik.iba_kotlin_lesson8

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import na.severinchik.iba_kotlin_lesson8.databinding.AddDialogFragmentBinding

class AddDialogFragment : DialogFragment() {


    lateinit var binding: AddDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_dialog_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogSave.setOnClickListener {
            val database = OldDatabase.getInstance(requireContext()).readableDatabase
            var contentValues = ContentValues()
            contentValues.put(
                DatabaseInfo.TableNotesInfo.COLUMN_TIME,
                "${binding.dialogTimepicker.hour} ${binding.dialogTimepicker.minute}"
            )
            contentValues.put(
                DatabaseInfo.TableNotesInfo.COLUMN_NOTE_NAME,
                binding.dialogName.text.toString()
            )
            contentValues.put(
                DatabaseInfo.TableNotesInfo.COLUMN_NOTE_INFO,
                binding.dialogInfo.text.toString()
            )

            database.insert(DatabaseInfo.TableNotesInfo.TABLE_NOTES_NAME, null, contentValues)
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}