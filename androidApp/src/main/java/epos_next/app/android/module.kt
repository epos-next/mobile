package epos_next.app.android

import epos_next.app.android.feats.home.HomeViewModel
import epos_next.app.android.feats.marks.main.MarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { HomeViewModel() }
    single { MarksScreenViewModel() }
}