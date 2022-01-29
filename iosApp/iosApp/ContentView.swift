import SwiftUI
import shared


struct ContentView: View {
    @ObservedObject var user: UserObservable
    @ObservedObject var homeViewModel: HomeViewModel
    
    init() {
        user = UserObservable()
        homeViewModel = HomeViewModel()
    }

    
    var body: some View {
        HStack {
            if user.state is UserState.Loading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
                    .scaleEffect(x: 1.5, y: 1.5, anchor: .center)
            }

            else if user.state is UserState.NotAuthorized {
                LoginScreenView()
            }

             else {
                 MainView()
             }
        }
        .environmentObject(user)
        .environmentObject(homeViewModel)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
