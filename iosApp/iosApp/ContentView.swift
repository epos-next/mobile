import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var user: UserObservable
    @ObservedObject var homeViewModel: HomeViewModel
    @ObservedObject var scheduleObservable: ScheduleObservable
    @ObservedObject var homeworkObservable: HomeworkObservable
    @ObservedObject var controlWorkObservable: ControlWorkObservable
    @ObservedObject var advertisementObservable: AdvertisementObservable
    @ObservedObject var marksObservable: MarksObservable
    @ObservedObject var nextLessonObservable: NextLessonObservable
    @ObservedObject var darkModeObservable: DarkModeObservable
    @Environment(\.colorScheme) var colorScheme
    
    init() {
        user = UserObservable()
        homeViewModel = HomeViewModel()
        scheduleObservable = ScheduleObservable()
        homeworkObservable = HomeworkObservable()
        controlWorkObservable = ControlWorkObservable()
        advertisementObservable = AdvertisementObservable()
        marksObservable = MarksObservable()
        nextLessonObservable = NextLessonObservable()
        darkModeObservable = DarkModeObservable()
        
        FetchBigDataObjectUseCaseImpl().invoke(completionHandler: { [self] data, error in
            self.scheduleObservable.reducer.loadTodaySchedule()
            self.marksObservable.reducer.loadMarks()
            self.nextLessonObservable.reducer.startCalculationProcess()
        })
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
        .environmentObject(homeworkObservable)
        .environmentObject(controlWorkObservable)
        .environmentObject(advertisementObservable)
        .environmentObject(marksObservable)
        .environmentObject(nextLessonObservable)
        .environmentObject(darkModeObservable)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
