import SwiftUI
import shared

struct HomeView: View {
    var body: some View {
        Text("Home")
    }
}

struct MarksView: View {
    var body: some View {
        Text("Marks")
    }
}

struct ProfileView: View {
    var body: some View {
        Text("Profile")
    }
}


struct ContentView: View {
    @State var selectedTab: String
    @ObservedObject var authStatus = AuthStatusObservable()
    
    init() {
        UITabBar.appearance().isHidden = false
        selectedTab = "home"
    }
    
    
    var body: some View {
        if (authStatus.state is AuthStatusState.Loading) {
            ProgressView()
        }
        
        else if (authStatus.state is AuthStatusState.NotAuthorized) {
            LoginScreenView()
        }
        
        else {
            ZStack {
                TabView (selection: $selectedTab) {
                    HomeView().tag("home")
                    MarksView().tag("marks")
                    ProfileView().tag("profile")
                }
                TabBarView(selectedTab: $selectedTab)
            }.ignoresSafeArea()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
