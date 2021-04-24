package com.example.zarbondistributionclient.ui.dialogs

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.zarbondistributionclient.R
import kotlinx.android.synthetic.main.buy_product_dialog.view.*

@Suppress("DEPRECATION")
class BuyProductDialog(context: Context, unit: String,price:Int) :
    BaseDialog(context, R.layout.buy_product_dialog) {

    private var querySt = ""
    private var listener: ((Double) -> Unit)? = null


    init {


        view.apply {
            unitText.text = unit

                cancelBuy.setOnClickListener{
              close()
            }

            buyProduct.setOnClickListener{
                var input = 0.0
                if(inputQuantity.text.toString().isNotEmpty()){
                    input = inputQuantity.text.toString().toDouble()
                }
                else inputQuantity.error = "Yaroqli miqdor kiriting!"

                if(input>0) {
                    listener!!.invoke(input)
                    close()
                }

            }


            inputQuantity.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if(inputQuantity.text.toString().isNotEmpty()){
                        sumPrice.text = (price*inputQuantity.text.toString().toDouble()).toString()
                    }
                    else    sumPrice.text =""
                }
            })

        }
    }

    fun setOnBuyProduct(f: ((Double) -> Unit)?) {
        listener = f
    }



}



