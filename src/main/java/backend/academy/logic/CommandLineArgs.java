package backend.academy.logic;

import backend.academy.services.Constant;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.util.List;
import lombok.Getter;

@Getter
public class CommandLineArgs {

    @Parameter(names = "--width", required = true)
    private int width;

    @Parameter(names = "--height", required = true)
    private int height;

    @Parameter(names = "--eqCount")
    private int eqCount = Constant.DEFAULT_EQCOUNT;

    @Parameter(names = "--iter", required = true)
    private int iterations;

    @Parameter(names = "--satur")
    private int saturations = Constant.DEFAULT_SATURATIONS;

    @Parameter(names = "--transform")
    private List<String> transformations;

    @Parameter(names = "--format")
    private String fileFormat = "jpg";

    public static CommandLineArgs parseArguments(String[] args) {
        CommandLineArgs cmdArgs = new CommandLineArgs();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(cmdArgs)
            .build();

        jCommander.parse(args);
        return cmdArgs;
    }

}
