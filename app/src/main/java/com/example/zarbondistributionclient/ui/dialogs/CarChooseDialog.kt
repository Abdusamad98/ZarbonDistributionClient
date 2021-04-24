package com.example.zarbondistributionclient.ui.dialogs

import android.content.Context
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.ui.adapters.CarSearchListAdapter
import kotlinx.android.synthetic.main.car_choose_dialog.view.*

@Suppress("DEPRECATION")
class CarChooseDialog(context: Context, clients: List<String>) :
    BaseDialog(context, R.layout.car_choose_dialog) {

    private var querySt = ""
    private var listener: ((String) -> Unit)? = null
    private var adapter = CarSearchListAdapter()

    init {
        view.apply {

            adapter.submitList(clients)
            recyclerCarSearch.adapter = adapter
            recyclerCarSearch.layoutManager = LinearLayoutManager(context)

            adapter.setOnClientChosenListener { name ->
                listener?.invoke(name)
                close()
            }

            cancelCarSearch.setOnClickListener {
                close()
            }

            val handler = Handler()
            searchCar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    handler.removeCallbacksAndMessages(null)
                    if (query != null) {
                        querySt = query.trim()
                        adapter.submitList(clients.filter {
                            it.contains(
                                querySt,
                                true
                            )
                        })
                        adapter.query = querySt
                        recyclerCarSearch.adapter = adapter
                        recyclerCarSearch.layoutManager = LinearLayoutManager(context)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        if (newText != null) {
                            querySt = newText.trim()
                            adapter.submitList(clients.filter {
                                it.contains(
                                    querySt,
                                    true
                                )
                            })
                            adapter.query = querySt
                            recyclerCarSearch.adapter = adapter
                            recyclerCarSearch.layoutManager = LinearLayoutManager(context)
                        }
                    }, 500)
                    return true
                }
            })

            val closeButton = searchCar.findViewById(R.id.search_close_btn) as ImageView
            closeButton.setOnClickListener {
                searchCar.setQuery(null, false)
                searchCar.clearFocus()
                //requiredActivity.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }

    fun setOnClientChosen(f: (( String) -> Unit)?) {
        listener = f
    }
}

