//
//  CounterScreen.swift
//  kviewmodel-swiftui
//
//  Created by Алексей Гладков on 07.06.2022.
//

import SwiftUI
import KViewModelShared

struct CounterScreen: View {
    @State private var isDetailOpen = false
    
    let testViewModel: TestViewModel = TestViewModel()
    
    
    var body: some View {
        ObservingView(statePublisher: statePublisher(testViewModel.viewStates())) { viewState in
            CounterView(viewState: viewState, incrementAction: {
                testViewModel.obtainEvent(viewEvent: .IncrementClick())
            }, decrementAction: {
                testViewModel.obtainEvent(viewEvent: .DecrementClick())
            }, openDetailAction: {
                testViewModel.obtainEvent(viewEvent: .DetailClick())
            })
                .onReceive(sharePublisher(testViewModel.viewActions()), perform: { action in
                    isDetailOpen = action is TestAction.OpenDetail
                })
                .sheet(isPresented: $isDetailOpen) {
                    DetailScreen()
                }
        }
    }
}

private struct CounterView: View {
    
    let viewState: TestViewState

    let incrementAction: () -> Void
    let decrementAction: () -> Void
    let openDetailAction: () -> Void
    
    var body: some View {
        VStack {
            Text("Hello, World")
            Spacer()
            
            HStack {
                Button {
                    decrementAction()
                } label: {
                    Text("-")
                        .font(.system(size: 24))
                }
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 16))

                Text("\(viewState.counter)")
                    .font(.system(size: 40))
                    
                Button {
                    incrementAction()
                } label: {
                    Text("+")
                        .font(.system(size: 24))
                }
                .padding(EdgeInsets(top: 0, leading: 16, bottom: 0, trailing: 0))
            }
            
            Spacer()
            
            Button("Open Detail") {
                openDetailAction()
            }
        }
    }
}
