package com.mina.localdatabaseapp.ui.userlist

import UsersListViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mina.localdatabaseapp.databinding.FragmentListBinding
import com.mina.localdatabaseapp.model.datamodel.UserData
import com.mina.localdatabaseapp.model.entitymodel.User
import com.mina.localdatabaseapp.ui.adapter.OnListItemClick
import com.mina.localdatabaseapp.ui.adapter.UserRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ListFragment : Fragment(), OnListItemClick {
    val TAG = "ListFragment"

    lateinit var binding: FragmentListBinding
    var userList: List<User> = emptyList()
    var userName: String? = null
//    lateinit var viewModel: UsersListViewModel
private val viewModel: UsersListViewModel by sharedViewModel()


    private val userRecyclerViewAdapter: UserRecyclerViewAdapter by lazy {
        UserRecyclerViewAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(requireActivity()).get(UsersListViewModel::class.java)
        userName = arguments?.getString("userName")
        binding.rvShowData.adapter = userRecyclerViewAdapter
        binding.edtJobTitle.setText("")
        binding.edtName.setText("")
        userRecyclerViewAdapter.onListItemClick = this
        getAllUsers()

        binding.btnAdd.setOnClickListener {
            Log.e(TAG, "btnAdd  ")
            viewModel.addUserAPI(
                User(
                    10,
                    binding.edtName.text.toString(),
                    binding.edtJobTitle.text.toString(), 0
                )
            )
            //add in local db
            //            listViewModel.addUser(
//                User(
//                    0,
//                    binding.edtName.text.toString(),
//                    binding.edtJobTitle.text.toString(),0
//                )
//            )

        }
//        binding.btnEdit.setOnClickListener {
//            Log.e(TAG, " btnEdit :${UserData.id} ")
//            UserData.id?.let { it1 ->
//                User(
//                    it1,
//                    binding.edtName.text.toString(),
//                    binding.edtJobTitle.text.toString(),
//                    0
//
//
//                )
//            }?.let { it2 -> viewModel.updateUser(it2) }
//
//            binding.edtJobTitle.setText("")
//            binding.edtName.setText("")
//
//        }
//        binding.btnSearch.setOnClickListener {
//            Log.e(TAG, " btnEdit :${(binding.edtSearch.text.toString()).toInt()} ")
//            viewModel.searchUser((binding.edtSearch.text.toString()).toInt())
//
//        }
        binding.btnGetAll.setOnClickListener {
            Log.e(TAG, " btnGetAll ")
            getAllUsers()
        }
        viewModel.usersAPILiveData.observe(viewLifecycleOwner, Observer {

            if (it != null) {

                Log.e("observe", "if it : $it ")
                userRecyclerViewAdapter.setList(it)
                binding.progressBar.visibility = View.GONE
                userRecyclerViewAdapter.notifyDataSetChanged()
                Toast.makeText(
                    context,
                    "  Connection  successfully ",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e("observe", " else it 1: $it ")
//                if (it != null) {
//                    userRecyclerViewAdapter.setList(it)
//                }
//                userRecyclerViewAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    context,
                    "no data Connection failed ",
                    Toast.LENGTH_SHORT
                ).show()

                Log.e("observe", " else it 2: $it ")
            }
        })


   viewModel.addUserAPILiveData.observe(viewLifecycleOwner, Observer {
       Log.e("observe", " addUserAPILiveData : $it ")
       if(it!=null){
           Log.e("observe", " addUserAPILiveData if  : $it ")

           Toast.makeText(context,"the user ${it.name} is add successfully",Toast.LENGTH_SHORT).show()
       }else{
           Log.e("observe", " addUserAPILiveData else  : $it ")

           Toast.makeText(context,"Connection failed ",Toast.LENGTH_SHORT).show()

       }
   })
    }

    //get users f
    private fun getAllUsers() {
        Log.e(TAG, "getAllUsers")
//        listViewModel.getUsers()
        viewModel.getUsersAPI()
        binding.progressBar.visibility = View.VISIBLE
    }

//    //get users from local
//    private fun getAllUsers() {
//        Log.e(TAG, "getAllUsers")
//        listViewModel.getUsers()
//        binding.progressBar.visibility = View.VISIBLE
//    }


    override fun onItemDelete(user: User) {
        //delete from API
//        viewModel.deleteUserAPI(user.id)
        //delete from local db
//        listViewModel.deleteUser(user)
        Toast.makeText(
            context,
            "The user ${user.name} is deleted successfully",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onItemEdit(user: User) {
        binding.edtJobTitle.setText(user.message)
        binding.edtName.setText(user.name)
        UserData.id = user.id
        UserData.name = user.name
        UserData.message = user.name
        Log.e("onItemEdit", "user.id : ${user.id}")

    }

//    override fun onItemSelect(user: User) {
//        binding.edtMessage.setText(user.name)
//        binding.edtName.setText(user.message)
//        UserData.id = user.id
//        UserData.name = user.name
//        UserData.message = user.name
//        Log.e("onItemEdit", "user.id : $user")
//        Log.e("onItemEdit", "user.id : ${user.id}")


    //    }
    override fun onItemSearchUser(user: User) {
//        viewModel.searchUser(user.id)
    }


}