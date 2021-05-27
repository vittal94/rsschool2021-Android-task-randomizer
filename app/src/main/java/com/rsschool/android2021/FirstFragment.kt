package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var editMin: EditText? = null
    private var editMax: EditText? = null
    private var listener: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        editMax = view.findViewById(R.id.max_value)
        editMin = view.findViewById(R.id.min_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"



        generateButton?.setOnClickListener {

            val min = if (editMin?.text.toString().isNotEmpty())
                editMin?.text.toString().toInt()
            else 0

            val max = if (editMax?.text.toString().isNotEmpty())
                editMax?.text.toString().toInt()
            else 0

            if (min in 0 until max && max > 0)
                listener?.openSecondFragment(min,max)
            else
                Toast.makeText(context,"Incorrect inputs",Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}