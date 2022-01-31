//
//  MainView.swift
//  iosApp
//
//  Created by Ярослав Зотов on 27.12.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    //    @EnvironmentObject var user: UserObservable
    
    init() {
        print("test")
    }
    
    var body: some View {
        Button("logout") {
            //            user.reducer.logout()
        }
    }
}

struct MainView: View {
    @State var selectedTab: String
    
    init() {
        UITabBar.appearance().isHidden = false
        selectedTab = "home"
    }
    
    var body: some View {
        ZStack(alignment: .bottom) {
            TabView (selection: $selectedTab) {
                HomeScreenView().tag("home")
                MarksScreenView().tag("marks")
                ProfileView().tag("profile")
            }
            TabBarView(selectedTab: $selectedTab)
        }
        .ignoresSafeArea()
        .navigationTitle("")
        .navigationBarHidden(true)
        
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
            .environmentObject(HomeViewModel())
    }
}
