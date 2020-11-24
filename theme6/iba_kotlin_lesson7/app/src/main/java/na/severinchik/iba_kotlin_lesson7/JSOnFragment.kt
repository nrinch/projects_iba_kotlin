package na.severinchik.iba_kotlin_lesson7

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import na.severinchik.iba_kotlin_lesson7.databinding.FragmentJSOnBinding
import java.io.BufferedReader
import java.io.File

class JSOnFragment : Fragment() {
    private var stringBuilder: StringBuilder? = null

    lateinit var binding:FragmentJSOnBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_j_s_on,container,false);
        requireActivity().externalCacheDir?.let {
            val fileName = it.absolutePath + "/PostJson.json"
            writeJSONtoFile(fileName)
            binding.outputJson.text = readJSONfromFile(fileName)

        }
requireActivity()
        return view
    }

    private fun writeJSONtoFile(s: String) {
        var tags = ArrayList<String>()
        tags.add("Android")
        tags.add("Angular")
        var post = Post("Json Tutorial", "www.nplix.com", "Pawan Kumar", tags)
        var gson = Gson()
        var jsonString: String = gson.toJson(post)
        val file = File(s)
        file.writeText(jsonString)
    }

    private fun readJSONfromFile(f: String): String {

        var gson = Gson()
        val bufferedReader: BufferedReader = File(f).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }

        var post = gson.fromJson(inputString, Post::class.java)
        stringBuilder = StringBuilder("Post Details\n---------------------")
        stringBuilder?.append("\nPost Heading: " + post.postHeading)
        stringBuilder?.append("\nPost URL: " + post.postUrl)
        stringBuilder?.append("\nPost Author: " + post.postAuthor)
        stringBuilder?.append("\nTags:")
        //get the all Tags

        post.postTag?.forEach { tag -> stringBuilder?.append(tag + ",") }
        //Display the all Json object in text View
        return stringBuilder.toString()

    }
}

class Post {
    var postHeading: String? = null
    var postUrl: String? = null
    var postAuthor: String? = null
    var postTag: List<String>? = null

    constructor() : super() {}

    constructor(
        PostHeading: String,
        PostUrl: String,
        PostAuthor: String,
        tags: List<String>
    ) : super() {
        this.postHeading = PostHeading
        this.postUrl = PostUrl
        this.postAuthor = PostAuthor
        this.postTag = tags
    }

}