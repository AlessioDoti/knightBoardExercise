## DESIGN
The application, after fetching the board and the commands, uses a factory method class for build the commands that are invoked in sequence.
The use of the factory method and the command pattern may seem Over Engineering, but those pattern are keeping the code SOLID and clean.
The decoupling level is not that high, given the lo complexity of the software.

## TESTS
Given that the full coverage wasn't expected, only the business logic is tested, avoiding testing the mere getter/setter usage.
The integration with the API is partially tested. End to end and functionality test are omitted because of the limited scope of the project.
The business logic unit tests comprends happy flows, error flows and corner cases.

## COMMENTS
In the code there are some comments that explains some choises and some assumtions taken while developing the app.
