package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.reportmodel.ReportData
import com.example.zarbondistributionclient.ui.adapters.ReportAdapter
import com.example.zarbondistributionclient.ui.viewmodels.ReportFragmentViewModel
import com.example.zarbondistributionclient.utils.showToast
import kotlinx.android.synthetic.main.report_fragment.*

class ReportFragment : Fragment(R.layout.report_fragment){

    private val reportViewModel: ReportFragmentViewModel by viewModels()
    lateinit var recycler: RecyclerView
    var chosenCategory = 0
   // lateinit var reportData: ArrayList<ReportData>
    private val productsAdapter by lazy { ReportAdapter() }
    var reportData: List<ReportData> = ArrayList()

    private var querySt = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recyclerReports)
        reportData = ArrayList<ReportData>()
        reportData = getReports()

        initProductsList(getReports())
        productsSetUp()

        backToHomeReport.setOnClickListener {
            findNavController().navigateUp()
        }

        refreshProducts.setOnRefreshListener {
          //  if (chosenCategory != 0) pageViewModel.getProducts(chosenCategory)
          //  Handler().postDelayed(Runnable {
          //      refreshProducts.isRefreshing = false
          //  }, 1000)
        }


        val handler = Handler()
        searchReportView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                if (query != null) {
                    querySt = query.trim()
                    initProductsList(reportData.filter { it.name.contains(querySt, true) })
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    if (newText != null) {
                        querySt = newText.trim()
                        initProductsList(reportData.filter { it.name.contains(querySt, true) })
                    }
                }, 500)
                return true
            }
        })
        val closeButton = searchReportView.findViewById(R.id.search_close_btn) as ImageView
        closeButton.setOnClickListener {
            reportViewModel.closeSearch()
        }

    }

    private fun getReports(): ArrayList<ReportData> {
        var reportData: ArrayList<ReportData> = ArrayList()
        reportData.add(ReportData("Engine Oil",20.0,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Car Motor",12.8,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Hp Lubricants",20.0,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Castrol Edge",10.5,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Something",39.0,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Engine Oil",20.0,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Car Motor",12.8,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Hp Lubricants",20.0,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Castrol Edge",10.5,"12/03/2021",1200.0,"image"))
        reportData.add(ReportData("Something",39.0,"12/03/2021",1200.0,"image"))
        return reportData
    }

    private val closeSearchObserver = Observer<Unit> {
        searchReportView.setQuery(null, false)
        searchReportView.clearFocus()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private val progressObserver = Observer<Boolean> {
        if (it) {
            productsProgressBar.visibility = View.VISIBLE
        } else {
            productsProgressBar.visibility = View.GONE
        }
    }

    private val connectionErrorObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }



    private val errorProductsObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
    }

    private val successProductsObserver = Observer<List<ReportData>> { products ->
       // productData = products
        initProductsList(products)
    }


    fun productsSetUp() {
//        reportViewModel.progressProductsLiveData.observe(viewLifecycleOwner, progressObserver)
        reportViewModel.closeLiveData.observe(viewLifecycleOwner, closeSearchObserver)
//        reportViewModel.errorProductsLiveData.observe(viewLifecycleOwner, errorProductsObserver)
//        reportViewModel.successProductsLiveData.observe(viewLifecycleOwner, successProductsObserver)
    }

    fun initProductsList(data: List<ReportData>) {
        productsAdapter.submitList(data)
        productsAdapter.query = querySt
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = productsAdapter
    }

}