import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    public void test() throws IOException {
        Main.out = spy(System.out);
        Main.reader = mockReader();

        Main.main(null);

        verify(Main.out, times(3)).println("All items in todo list:");
        verify(Main.out, times(6)).println("=======================");
        verify(Main.out, times(3)).println("Grocery Shopping");
        verify(Main.out, times(3)).println("Fitness");
        verify(Main.out, times(2)).println("Sleeping");
        verify(Main.out, times(1)).println("Finished adding items to todo list");
    }

    private BufferedReader mockReader() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        when(mockReader.readLine()).thenAnswer(answer());
        return mockReader;
    }

    private Answer<String> answer() {
        return new Answer<>() {
            int count = 0;

            @Override
            public String answer(InvocationOnMock invocation) {
                if (count++ == 0) {
                    return "Sleeping";
                }
                return "";
            }
        };
    }
}