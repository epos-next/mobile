import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var user: UserObservable
    @ObservedObject var homeViewModel: HomeViewModel
    @ObservedObject var scheduleObservable: ScheduleObservable
    
    init() {
        user = UserObservable()
        homeViewModel = HomeViewModel()
        scheduleObservable = ScheduleObservable()
        
//        FetchBigDataObjectUseCaseImpl().invoke(completionHandler: { data, error in
//
//        })
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
        .environmentObject(scheduleObservable)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
