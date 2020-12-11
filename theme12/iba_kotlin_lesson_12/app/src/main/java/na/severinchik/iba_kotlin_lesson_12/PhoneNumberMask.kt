package na.severinchik.iba_kotlin_lesson_12

import android.text.Editable
import android.text.TextWatcher

class PhoneNumberMask (private val mask:String) : TextWatcher {

    var isRunning:Boolean =false
    var isDeleting:Boolean = false

    override fun afterTextChanged(editable: Editable?) {
        if(isRunning|| isDeleting){
            return
        }
        isRunning = true


        val editableLength: Int = editable?.length ?: 0
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable?.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable?.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }



        isRunning=false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count>after
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    companion object{
        fun build():PhoneNumberMask{
            return PhoneNumberMask("(##)###-##-##")
        }
    }

}