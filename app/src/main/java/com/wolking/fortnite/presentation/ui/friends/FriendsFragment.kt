package com.wolking.fortnite.presentation.ui.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wolking.fortnite.databinding.FragmentFriendsBinding
import com.wolking.fortnite.presentation.ui.friends.componentes.FriendsList
import com.wolking.fortnite.presentation.ui.friends.viewmodel.FriendsViewModel
import com.wolking.fortnite.presentation.ui.theme.FortnaticosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    private val friendViewModel: FriendsViewModel by viewModels()
    private lateinit var binding: FragmentFriendsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFriendsBinding.inflate(inflater, container, false)
        friendViewModel.getFriends()
        return binding.root.apply {
            binding.composeView.apply {
                setContent {
                    FortnaticosTheme {
                        FriendsList(
                            viewModel = friendViewModel
                        )
                    }
                }
            }
        }
    }
}