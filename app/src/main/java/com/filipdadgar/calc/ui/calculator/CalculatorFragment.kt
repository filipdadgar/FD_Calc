package com.filipdadgar.calc.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.filipdadgar.calc.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        viewModel.display.observe(viewLifecycleOwner) {
            binding.calculatorDisplay.text = it
        }

        val digitListener = View.OnClickListener { v ->
            val id = v.id
            val digit = when (id) {
                binding.btn0.id -> "0"
                binding.btn1.id -> "1"
                binding.btn2.id -> "2"
                binding.btn3.id -> "3"
                binding.btn4.id -> "4"
                binding.btn5.id -> "5"
                binding.btn6.id -> "6"
                binding.btn7.id -> "7"
                binding.btn8.id -> "8"
                binding.btn9.id -> "9"
                else -> null
            }
            digit?.let { viewModel.onDigit(it) }
        }
        binding.btn0.setOnClickListener(digitListener)
        binding.btn1.setOnClickListener(digitListener)
        binding.btn2.setOnClickListener(digitListener)
        binding.btn3.setOnClickListener(digitListener)
        binding.btn4.setOnClickListener(digitListener)
        binding.btn5.setOnClickListener(digitListener)
        binding.btn6.setOnClickListener(digitListener)
        binding.btn7.setOnClickListener(digitListener)
        binding.btn8.setOnClickListener(digitListener)
        binding.btn9.setOnClickListener(digitListener)

        binding.btnDot.setOnClickListener { viewModel.onDot() }
        binding.btnAdd.setOnClickListener { viewModel.onOperator("+") }
        binding.btnSubtract.setOnClickListener { viewModel.onOperator("-") }
        binding.btnMultiply.setOnClickListener { viewModel.onOperator("×") }
        binding.btnDivide.setOnClickListener { viewModel.onOperator("÷") }
        binding.btnEquals.setOnClickListener { viewModel.onEquals() }
        binding.btnClear.setOnClickListener { viewModel.onClear() }
        binding.btnDelete.setOnClickListener { viewModel.onDelete() }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
