package ru.codeoverflow.junctionhack.ui.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.entity.tours.TourModel
import ru.codeoverflow.junctionhack.model.storage.Cache
import ru.codeoverflow.junctionhack.ui.fragment.tours.adapter.toursAdapterDelegate
import ru.codeoverflow.junctionhack.viewmodel.profile.ProfileViewModel

class ProfileFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_profile

    private val viewModel: ProfileViewModel by viewModel()

    private val cache: Cache by inject()

    private val adapter: ListDelegationAdapter<List<TourModel>> by lazy {
        ListDelegationAdapter(
            toursAdapterDelegate {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToDetailTourFragment(
                        it.id
                    )
                )
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            progressBar.isVisible = it
        })

        tvFavoriteTitle.isVisible = cache.favoriteTours?.isNotEmpty() ?: false
        rvTours.isVisible = cache.favoriteTours?.isNotEmpty() ?: false

        rvTours.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollVertically() = false
        }
        rvTours.adapter = adapter

        viewModel.user.observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext())
                .load(it?.photo)
                .transform(
                    RoundedCorners(resources.getDimension(R.dimen.radius_18).toInt()),
                    CenterCrop()
                )
                .into(ivAvatar)

            tvName.text = it.name
            tvGeo.text = it?.location?.address
            adapter.items = cache.favoriteTours
            adapter.notifyDataSetChanged()
        })

    }
}