package com.example.e_commerce.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.ui.ApiService.BaseApiService
import com.example.e_commerce.ui.ApiService.ProductsService
import com.example.e_commerce.ui.DataTypes.ProductType
import com.example.e_commerce.ui.fragments.Adapters.fragment_home_adapter_for_common_card
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fragment_home : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var baseApi: BaseApiService
    private var productList: List<ProductType> = emptyList()
    private lateinit var adapter: fragment_home_adapter_for_common_card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseApi = BaseApiService(requireContext().getString(R.string.BASE_URL))
        fetchProducts()
        recyclerView = view.findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        adapter = fragment_home_adapter_for_common_card(productList)
        recyclerView.adapter = adapter

    }

    private fun fetchProducts() {
        val productService = baseApi.createService(ProductsService::class.java)
        productService.getAllProducts().enqueue(object : Callback<List<ProductType>> {
            override fun onResponse(call: Call<List<ProductType>>, response: Response<List<ProductType>>) {
                if (response.isSuccessful) {
                    productList = response.body() ?: emptyList()
                    Log.e("FragmentHome", "Get Products ${ response.body()}");
//                    adapter.updateData(productList)
                } else {
                    Log.e("FragmentHome", "Error fetching products: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Failed to load products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ProductType>>, t: Throwable) {
                Log.e("FragmentHome", "API call failed", t)
                Toast.makeText(context, "Failed to load products", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
