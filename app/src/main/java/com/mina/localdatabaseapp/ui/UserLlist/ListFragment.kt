package com.mina.localdatabaseapp.ui.UserLlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.ahmedtawfik.kotlinappnavigation.model.local.roomdb.LocalRepositoryImp
import com.ahmedtawfik.kotlinappnavigation.model.local.roomdb.UserDatabase
import com.mina.localdatabaseapp.databinding.FragmentListBinding
import com.mina.localdatabaseapp.model.datamodel.UserData
import com.mina.localdatabaseapp.ui.adapter.OnListItemClick
import com.mina.localdatabaseapp.ui.adapter.UserRecyclerView
import kotlinx.coroutines.*

class ListFragment : Fragment(), OnListItemClick {

    lateinit var binding: FragmentListBinding

    //    private val viewModel: UsersListViewModel
    var userList: List<User> = emptyList()
    var userName: String? = null
    lateinit var localRepositoryImp: LocalRepositoryImp
    private val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView()
    }
//    private val db:UserDatabase by lazy {
//        UserDatabase.getInstance(requireContext())
//    }

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

//        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)

        userName = arguments?.getString("userName")
        binding.rvShowData.adapter = userRecyclerView
        var db = UserDatabase.getInstance(requireContext())
        Log.e("getAllUsers", "db :$db ")

        localRepositoryImp = LocalRepositoryImp(db)
        Log.e("getAllUsers", "localRepositoryImp :$localRepositoryImp ")

        getAllUsers()

        binding.btnAdd.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db.userDao().insertOrUpdateUser(
                    User(
                        0,
                        binding.edtName.text.toString(),
                        binding.edtMessage.text.toString(),

                        )
                )
            }
            binding.btnEdit.setOnClickListener {
                Log.e("getAllUsers", " UserData.id?1 :${ UserData.id} ")

                GlobalScope.launch(Dispatchers.IO) {
                    Log.e("getAllUsers", " UserData.id?2 :${ UserData.id} ")
                    db.userDao().updateUser(


                                UserData.id?.let { it1 ->
                            User(
                                it1,
                                binding.edtName.text.toString(),
                                binding.edtMessage.text.toString(),


                                )
                        }
                    )
                }
                binding.edtMessage.setText("")
                binding.edtName.setText("")
                getAllUsers()
            }
            Log.e("getAllUsers", "User : ")

            getAllUsers()
            Log.e("getAllUsers", "userList : $userList ")

            binding.edtMessage.setText("")
            binding.edtName.setText("")
        }

        userRecyclerView.onListItemClick = this


    }

    private fun getAllUsers() {
        Log.e("getAllUsers", "getAllUsers")
        GlobalScope.launch(Dispatchers.IO) {

            var returnUsers = async {
                localRepositoryImp.getUsers()

            }

            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.VISIBLE
                userList = returnUsers.await()
                binding.progressBar.visibility = View.GONE
                userRecyclerView.setList(userList)
                Log.e("getAllUsers", "returnusers : ${returnUsers}")

            }

        }
    }

//    private fun getAllUsers() {

//        viewModel.getUsersAPI()
//        binding.progressBar.visibility = View.VISIBLE
//        viewModel.getUsersList()
//    }

    override fun onItemDelete(user: User) {

        GlobalScope.launch(Dispatchers.IO) {
            localRepositoryImp.deleteUser(user)
        }

        Toast.makeText(
            context,
            "The user ${user.name} is deleted successfully",
            Toast.LENGTH_SHORT
        ).show()

        getAllUsers()
    }

    override fun onItemEdit(user: User) {
        binding.edtMessage.setText(user.name)
        binding.edtName.setText(user.message)
        UserData.id = user.id
        UserData.name = user.name
        UserData.message = user.name
        Log.e("onItemEdit", "user.id : $user")
        Log.e("onItemEdit", "user.id : ${user.id}")


    }

    override fun onItemSelect(user: User) {
//        binding.edtMessage.setText(user.name)
//        binding.edtName.setText(user.message)
//        UserData.id = user.id
//        UserData.name = user.name
//        UserData.message = user.name
//        Log.e("onItemEdit", "user.id : $user")
//        Log.e("onItemEdit", "user.id : ${user.id}")


    }
}